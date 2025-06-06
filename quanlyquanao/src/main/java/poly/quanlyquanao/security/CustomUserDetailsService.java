package poly.quanlyquanao.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.repository.UserRepositoty;
import poly.quanlyquanao.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepositoty userRepositoty;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepositoty.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy user: " + username);
        }
        return new CustomUserDetails(userOpt.get());
    }
}
