package pl.platrykp.cubeformservice.resources;

import net.minidev.json.annotate.JsonIgnore;
import pl.platrykp.cubeformservice.models.User;

import java.sql.Timestamp;

public class UserOtherResource {

    @JsonIgnore
    private User user;

    public UserOtherResource(User user){
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Timestamp getCreationDate() {
        return user.getCreationDate();
    }
}
