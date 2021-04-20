package pl.platrykp.cubeformservice.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.platrykp.cubeformservice.models.User;
import pl.platrykp.cubeformservice.repositories.UserRepository;
import pl.platrykp.cubeformservice.requests.RegisterRequest;
import pl.platrykp.cubeformservice.responseentities.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/auth/users")
    public Object Register(
            HttpServletResponse response,
            @RequestBody RegisterRequest registerRequest){

        Optional<User> inDatabase = userRepository.findByLoginOrEmail(registerRequest.login, registerRequest.email);
        if (inDatabase.isPresent()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if(inDatabase.get().getLogin().equals(registerRequest.login))
                return new ErrorResponse("login already taken");
            else
                return new ErrorResponse("email already taken");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.password);

        User newUser = new User(
                registerRequest.login,
                registerRequest.email,
                hashedPassword);

        userRepository.save(newUser);

        return null;
    }


}
