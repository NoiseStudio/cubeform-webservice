package pl.platrykp.cubeformservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.platrykp.cubeformservice.details.AuthUserDetails;
import pl.platrykp.cubeformservice.models.UserEntity;
import pl.platrykp.cubeformservice.repositories.UserRepository;

import java.util.Optional;

public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isPresent())
            return new AuthUserDetails(user.get());

        throw new UsernameNotFoundException("User not found");
    }
}
