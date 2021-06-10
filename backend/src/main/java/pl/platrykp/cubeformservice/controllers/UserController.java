package pl.platrykp.cubeformservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.platrykp.cubeformservice.details.AuthUserDetails;
import pl.platrykp.cubeformservice.models.UserEntity;
import pl.platrykp.cubeformservice.repositories.UserRepository;
import pl.platrykp.cubeformservice.resources.UserMeResource;
import pl.platrykp.cubeformservice.resources.UserOtherResource;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/@me")
    public UserMeResource me(Authentication authentication){
        return new UserMeResource((AuthUserDetails) authentication.getPrincipal());
    }

    @GetMapping("/users/{id}")
    public UserOtherResource getUser(@PathVariable UUID id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent())
            return new UserOtherResource(user.get());

        throw new ResourceNotFoundException();
    }
}
