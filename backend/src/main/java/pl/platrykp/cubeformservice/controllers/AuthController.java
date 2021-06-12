package pl.platrykp.cubeformservice.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.platrykp.cubeformservice.components.JwtTokenUtil;
import pl.platrykp.cubeformservice.configurations.WebSecurityConfiguration;
import pl.platrykp.cubeformservice.details.AuthUserDetails;
import pl.platrykp.cubeformservice.models.RoleEntity;
import pl.platrykp.cubeformservice.models.UserEntity;
import pl.platrykp.cubeformservice.repositories.RoleRepository;
import pl.platrykp.cubeformservice.repositories.UserRepository;
import pl.platrykp.cubeformservice.requests.LoginRequest;
import pl.platrykp.cubeformservice.requests.RegisterRequest;
import pl.platrykp.cubeformservice.resources.UserMeResource;
import pl.platrykp.cubeformservice.responseentities.ErrorResponse;
import pl.platrykp.cubeformservice.responseentities.HttpCodeResponse;
import pl.platrykp.cubeformservice.responseentities.LoginSuccessResponse;
import pl.platrykp.cubeformservice.util.JsonResponse;
import pl.platrykp.cubeformservice.util.Role;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public Object register(
            HttpServletResponse response,
            @RequestBody RegisterRequest registerRequest){

        Optional<UserEntity> inDatabase = userRepository.findByUsernameOrEmail(registerRequest.username, registerRequest.email);
        if (inDatabase.isPresent()) {
            logger.info("Failed attempt to create user: username or email already taken");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if(inDatabase.get().getUsername().equals(registerRequest.username))
                return new ErrorResponse("username already taken");
            else
                return new ErrorResponse("email already taken");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.password);

        Optional<RoleEntity> role = roleRepository.findById(Role.USER.getId());
        if(role.isEmpty())
            return JsonResponse.internalServerError("Something really bad happen");

        UserEntity newUserEntity = new UserEntity(
                registerRequest.username,
                registerRequest.email,
                hashedPassword,
                role.get());


        logger.info("New user created");
        return new UserMeResource(userRepository.save(newUserEntity));
    }


    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userDetails);

            return new LoginSuccessResponse(jwt, userDetails.getUsername(), userDetails.getRoleEntity());
        }catch (Exception er){
            logger.info("Failed attempt to login: invalid username or password");
            return JsonResponse.badRequest("invalid username or password");
        }
    }

    @PostMapping("/refreshToken")
    public Object refreshToken(Authentication authentication){
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if(userDetails == null || !authentication.isAuthenticated())
            return JsonResponse.forbidden("You are not authorized");

        String jwt = jwtTokenUtil.generateToken(userDetails);

        return new LoginSuccessResponse(jwt, userDetails.getUsername(), userDetails.getRoleEntity());
    }

}
