package pl.platrykp.cubeformservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @Size(min = 4, max = 20)
    public String username;
    @NotNull
    @Size(min = 8, max = 32)
    public String password;

    @NotNull
    @Email
    public String email;

}
