package pl.platrykp.cubeformservice.resources;

import net.minidev.json.annotate.JsonIgnore;
import pl.platrykp.cubeformservice.models.UserEntity;

import java.sql.Timestamp;

public class UserOtherResource {

    @JsonIgnore
    private final UserEntity userEntity;

    public UserOtherResource(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public String getUsername() {
        return userEntity.getUsername();
    }

    public Timestamp getCreationDate() {
        return userEntity.getCreationDate();
    }
}
