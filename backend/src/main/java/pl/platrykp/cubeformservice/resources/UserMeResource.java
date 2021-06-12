package pl.platrykp.cubeformservice.resources;

import net.minidev.json.annotate.JsonIgnore;
import pl.platrykp.cubeformservice.models.RoleEntity;
import pl.platrykp.cubeformservice.models.UserEntity;

import java.sql.Timestamp;
import java.util.UUID;

public class UserMeResource {

    @JsonIgnore
    private final UserEntity user;

    public UserMeResource(UserEntity user){
        this.user = user;
    }

    public UUID getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Timestamp getCreationDate() {
        return user.getCreationDate();
    }

    private RoleEntity getRole() {
        return user.getRole();
    }
}
