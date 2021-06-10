package pl.platrykp.cubeformservice.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.platrykp.cubeformservice.models.UserEntity;
import pl.platrykp.cubeformservice.util.Role;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public class AuthUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public AuthUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
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

    public UUID getId() {
        return userEntity.getId();
    }

    public String getEmail() {
        return userEntity.getEmail();
    }

    public Timestamp getCreationDate() {
        return userEntity.getCreationDate();
    }

    public Role getRole(){ return Role.fromRoleEntity(userEntity.getRole()); }
}
