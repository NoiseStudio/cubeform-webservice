package pl.platrykp.cubeformservice.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.platrykp.cubeformservice.configurations.WebSecurityConfiguration;
import pl.platrykp.cubeformservice.models.User;
import pl.platrykp.cubeformservice.repositories.UserRepository;
import pl.platrykp.cubeformservice.requests.RegisterRequest;
import pl.platrykp.cubeformservice.responseentities.ErrorResponse;
import pl.platrykp.cubeformservice.responseentities.HttpCodeResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(WebSecurityConfiguration.REGISTER_PAGE_PATH)
    public Object Register(
            HttpServletResponse response,
            @RequestBody RegisterRequest registerRequest){

        Optional<User> inDatabase = userRepository.findByUsernameOrEmail(registerRequest.username, registerRequest.email);
        if (inDatabase.isPresent()) {
            logger.info("Failed attempt to create user: username or email already taken");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if(inDatabase.get().getUsername().equals(registerRequest.username))
                return new ErrorResponse("username already taken");
            else
                return new ErrorResponse("email already taken");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.password);

        User newUser = new User(
                registerRequest.username,
                registerRequest.email,
                hashedPassword);

        userRepository.save(newUser);
        logger.info("New user created");
        return null;
    }


    @RequestMapping(value = WebSecurityConfiguration.LOGIN_SUCCESS_PAGE_PATH,  method = {RequestMethod.POST, RequestMethod.GET})
    public HttpCodeResponse loginSuccess(){
        return new HttpCodeResponse(200, "logged in", "POST", WebSecurityConfiguration.LOGIN_PAGE_PATH);
    }

    @RequestMapping(value = WebSecurityConfiguration.LOGIN_FAILED_PAGE_PATH, method = {RequestMethod.POST, RequestMethod.GET})
    public void loginFailed(){
        throw new AccessDeniedException("Wrong username or password");
    }

    @RequestMapping(value = WebSecurityConfiguration.LOGOUT_DONE_PAGE_PATH, method = {RequestMethod.POST, RequestMethod.GET})
    public HttpCodeResponse logoutDone(){
        return new HttpCodeResponse(200, "logout", "POST", WebSecurityConfiguration.LOGOUT_PAGE_PATH);
    }

}
