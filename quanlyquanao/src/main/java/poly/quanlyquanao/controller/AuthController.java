package poly.quanlyquanao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.ChangePasswordRequest;
import poly.quanlyquanao.dto.LoginRequestDTO;
import poly.quanlyquanao.dto.RegisterRequestDTO;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.security.CustomUserDetails;
import poly.quanlyquanao.security.JwtUtil;
import poly.quanlyquanao.service.EmailService;
import poly.quanlyquanao.service.Impl.IUserService;

@CrossOrigin(origins = "*") // hoặc chỉ định frontend cụ thể
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority(); // ví dụ: "ROLE_ADMIN"

            String token = jwtUtil.generateTokenWithRole(userDetails.getUsername(), role);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "username", userDetails.getUsername(),
                    "role", role
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Sai tên đăng nhập hoặc mật khẩu"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO signUpRequest) {
        try {
            User saveUser = userService.registerUser(signUpRequest);

            if(saveUser.getEmail() != null && !saveUser.getEmail().isBlank()) {
                emailService.sendVerificationEmail(saveUser.getEmail(), saveUser.getEmailVerificationToken());
            }
            String role = saveUser.getRole().getRoleName();
            String token = jwtUtil.generateTokenWithRole(saveUser.getUsername(), role);

            return ResponseEntity.ok(Map.of(
                    "token", token
            ));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
        String result = userService.verifyUser(token);

        if (result.contains("thành công")) {
            return ResponseEntity.ok(Map.of("message", result));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", result));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> payload) {
        try {
            userService.generateResetToken(payload.get("email"));
            return ResponseEntity.ok(Map.of("message", "Đã gửi email đặt lại mật khẩu."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
                                           @RequestBody Map<String, String> payload) {
        try {
            userService.resetPassword(token, payload.get("newPassword"));
            return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cần đăng nhập
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Authentication authentication) {
        try {
            String username = authentication.getName();
            userService.changePassword(username, request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
