package pl.platrykp.cubeformservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.platrykp.cubeformservice.models.RoleEntity;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessResponse {

    private String jwt;
    private UUID userId;
    private String username;
    private RoleEntity role;

}
