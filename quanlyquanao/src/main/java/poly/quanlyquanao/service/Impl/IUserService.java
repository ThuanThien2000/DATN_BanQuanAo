package poly.quanlyquanao.service.Impl;

import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import poly.quanlyquanao.dto.RegisterRequestDTO;
import poly.quanlyquanao.dto.UpdateProfileDTO;
import poly.quanlyquanao.model.User;

public interface IUserService {
//    Page<User> getPageUser(Pageable pageable);
    List<User> findAll();
    User add(User user);
    List<User> findByStatusOne();
    User updateUser(Long id, User updatedUser);
    User getUserById(Long id);
    User getUserByUsername(String username);
    void deleteUser(Long id);

    User registerUser(RegisterRequestDTO registerRequest);
    String verifyUser(String token);
    void changePassword(String username, String oldPass, String newPass);
    void generateResetToken(String email);
    void resetPassword(String token, String newPassword);

    // Customer
    User updateCustomerProfile(String username, UpdateProfileDTO dto);

}