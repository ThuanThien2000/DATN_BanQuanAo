package poly.quanlyquanao.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Role;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.repository.UserRepository;

@Service
public class UserService implements poly.quanlyquanao.service.Impl.IUserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public Page<User> getPageUser(Pageable pageable){
        return userRepository.findAll(pageable);
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
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy người dùng với id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationdate(LocalDateTime.now());

        Role defaultRole = new Role();
        defaultRole.setId(3L); // ID vai trò mặc định là 2
        user.setRole(defaultRole);

        user.setStatus(0); // Đăng ký mặc định là 0

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

}