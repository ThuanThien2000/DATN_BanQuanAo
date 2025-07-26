package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.dto.UpdateProfileDTO;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.service.Impl.IUserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private IUserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO dto, Principal principal) {
        try {
            User updatedUser = userService.updateCustomerProfile(principal.getName(), dto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
