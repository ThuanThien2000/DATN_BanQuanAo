package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.UserDTO;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.model.Role;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        Role role = user.getRole();
        return new UserDTO(
            user.getUsername(),
            user.getPassword(),
            user.getFullname(),
            user.getGender(),
            user.getPhonenumber(),
            user.getAddress(),
            user.getEmail(),
            role != null ? role.getId() : null,
            role != null ? role.getRoleName() : null,
            user.getEmailVerificationToken(),
            user.getStatus()
        );
    }
}
