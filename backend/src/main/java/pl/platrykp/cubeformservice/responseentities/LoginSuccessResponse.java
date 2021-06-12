package pl.platrykp.cubeformservice.responseentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.platrykp.cubeformservice.models.RoleEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessResponse {

    private String jwt;
    private String username;
    private RoleEntity role;

}
