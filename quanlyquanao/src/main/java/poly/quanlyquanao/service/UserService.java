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
import poly.quanlyquanao.repository.UserRepositoty;
import poly.quanlyquanao.service.Impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl{
    @Autowired
    UserRepositoty userRepositoty;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
//    public List<Map<Long, Object>> getAllUser(){
//        return userRepositoty.findAllUser();
//    }
//    public List<User> getAllUser(){
//        return userRepositoty.findAll();
//    }
    
    @Override
    public Page<User> getPageUser(Pageable pageable){
        return userRepositoty.findAll(pageable);
    }
    
    @Override
    public User add(User user){
        if (user.getRegistrationdate() == null) {
            user.setRegistrationdate(LocalDateTime.now());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepositoty.save(user);
    }

    @Override
    public List<User> findByStatusOne() {
        return userRepositoty.findByStatusOne();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepositoty.findById(id);
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

            return userRepositoty.save(existingUser);
        } else {
            throw new RuntimeException("Không tìm thấy người dùng với id: " + id);
        }
    }

    // tìm đơn
    @Override
    public User getUserById(Long id) {
        return userRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: "+ id));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepositoty.existsById(id)) {
            throw new RuntimeException("Không tìm thấy người dùng với id: " + id);
        }
        userRepositoty.deleteById(id);
    }

}