package pl.platrykp.cubeformservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterServerRequest {
    private String name;
    private String gameMode;
    private boolean mods;
}
