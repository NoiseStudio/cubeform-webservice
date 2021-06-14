package pl.platrykp.cubeformservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginServerRequest {

    private String accessToken;
    private int maxPlayers;
    private String address;
    private int port;

}
