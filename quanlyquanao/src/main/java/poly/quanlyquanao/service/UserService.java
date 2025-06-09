package poly.quanlyquanao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.repository.UserRepository;
import poly.quanlyquanao.service.Impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl{
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

}