package pl.platrykp.cubeformservice.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.platrykp.cubeformservice.models.User;

import java.sql.Timestamp;
import java.util.Collection;

public class AuthUserDetails implements UserDetails {

    private User user;

    public AuthUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Timestamp getCreationDate() {
        return user.getCreationDate();
    }
}
