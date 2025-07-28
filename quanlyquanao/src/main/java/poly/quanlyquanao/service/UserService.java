package poly.quanlyquanao.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.dto.RegisterRequestDTO;
import poly.quanlyquanao.model.Role;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.repository.UserRepository;

@Service
public class UserService implements poly.quanlyquanao.service.Impl.IUserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;
    
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    @Override
    public User add(User user){
        if (user.getRegistrationdate() == null) {
            user.setRegistrationdate(LocalDateTime.now());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public List<User> findByStatusOne() {
        return userRepository.findByStatusOne();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Cập nhật thông tin
            existingUser.setUsername(updatedUser.getUsername());

            // Mã hóa lại mật khẩu nếu có thay đổi
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            existingUser.setFullname(updatedUser.getFullname());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setPhonenumber(updatedUser.getPhonenumber());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setStatus(updatedUser.getStatus());

            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("Không tìm thấy người dùng với id: " + id);
        }
    }

    // tìm đơn
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: "+ id));
    }
    
    @Override
    public User getUserByUsername(String username) {
    	return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với : "+ username));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy người dùng với id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Xác thực bằng email
    @Override
    public User registerUser(RegisterRequestDTO registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        if (userRepository.findByPhonenumber(registerRequest.getPhonenumber()).isPresent()) {
            throw new RuntimeException("Số điện thoại đã được sử dụng");
        }

        // Tạo user mới
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullname(registerRequest.getFullname());
        user.setGender(registerRequest.isGender());
        user.setPhonenumber(registerRequest.getPhonenumber());
        user.setAddress(registerRequest.getAddress());
        user.setEmail(registerRequest.getEmail());

        user.setRegistrationdate(LocalDateTime.now()); // Mặc định ngày tháng năm hiện tại
        // Mặc định vai trò
        Role defaultRole = new Role();
        defaultRole.setId(3L);
        user.setRole(defaultRole);

        user.setStatus(0); // Mặc định trạng thái

        String token = java.util.UUID.randomUUID().toString();
        user.setEmailVerificationToken(token);
        user.setTokenCreationTime(Timestamp.valueOf(LocalDateTime.now()));

        return userRepository.save(user);
    }

    @Override
    public String verifyUser(String token) {
        Optional<User> optionalUser = userRepository.findByEmailVerificationToken(token);
        if (optionalUser.isEmpty()) {
            return "Token không hợp lệ hoặc đã hết hạn.";
        }

        User user = optionalUser.get();
        // Kiểm tra thời hạn token (ví dụ: 24 giờ)
        LocalDateTime creationTime = user.getTokenCreationTime().toLocalDateTime();
        if (creationTime.plusHours(24).isBefore(LocalDateTime.now())) {
            return "Token đã hết hạn. Vui lòng đăng ký lại.";
        }

        user.setStatus(1); // Đã kích hoạt
        user.setEmailVerificationToken(null); // Xóa token
        user.setTokenCreationTime(null);

        userRepository.save(user);
        return "Tài khoản của bạn đã được xác minh thành công!";
    }

    @Override
    public void changePassword(String username, String oldPass, String newPass) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
    }

    @Override
    public void generateResetToken(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy email này.");
        }

        User user = userOpt.get();
        String token = UUID.randomUUID().toString();
        user.setEmailVerificationToken(token);
        user.setTokenCreationTime(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);

        // Gửi email
        emailService.sendResetPasswordEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByEmailVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Token không hợp lệ"));

        LocalDateTime createdTime = user.getTokenCreationTime().toLocalDateTime();
        if (createdTime.plusHours(1).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token đã hết hạn.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setEmailVerificationToken(null);
        user.setTokenCreationTime(null);

        userRepository.save(user);
    }
}