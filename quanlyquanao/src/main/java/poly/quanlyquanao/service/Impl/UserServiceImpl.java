package poly.quanlyquanao.service.Impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poly.quanlyquanao.model.User;


public interface UserServiceImpl {
    Page<User> getPageUser(Pageable pageable);
    User add(User user);
    List<User> findByStatusOne();
    User updateUser(Long id, User updatedUser);
    User getUserById(Long id);
    void deleteUser(Long id);
}