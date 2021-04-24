package pl.platrykp.cubeformservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.platrykp.cubeformservice.details.AuthUserDetails;
import pl.platrykp.cubeformservice.models.User;
import pl.platrykp.cubeformservice.repositories.UserRepository;

import java.util.Optional;

public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent())
            return new AuthUserDetails(user.get());

        throw new UsernameNotFoundException("User not found");
    }
}
