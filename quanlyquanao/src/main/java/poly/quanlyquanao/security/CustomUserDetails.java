package poly.quanlyquanao.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import poly.quanlyquanao.model.User;

public class CustomUserDetails implements UserDetails{
    
    private final User user;
    
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singleton(
            new SimpleGrantedAuthority ("ROLE_" + user.getRole().getRoleName())
//        new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName().toUpperCase())
//        → Khi role là "Admin" thì kết quả là "ROLE_ADMIN" — khớp với hasRole("ADMIN").
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();    
    }

    @Override
    public String getUsername() {
        return user.getUsername();    
    }

    @Override
    public boolean isAccountNonExpired() {
//        return true; // bạn có thể kiểm tra `user.getStatus()` ở đây nếu muốn
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
//        return true;
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return true;
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1; // chỉ cho phép user có status = 1
//        return UserDetails.super.isEnabled();
    }

//    public String getRole() {
//        return user.getRole().getRoleName();
//    }
//
//    public User getUserEntity() {
//        return this.user;
//    }
}
