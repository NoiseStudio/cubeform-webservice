package pl.platrykp.cubeformservice.resources;

import net.minidev.json.annotate.JsonIgnore;
import pl.platrykp.cubeformservice.details.AuthUserDetails;

import java.sql.Timestamp;

public class UserMeResource {

    @JsonIgnore
    private final AuthUserDetails user;

    public UserMeResource(AuthUserDetails user){
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public Timestamp getCreationDate() {
        return user.getCreationDate();
    }
}
