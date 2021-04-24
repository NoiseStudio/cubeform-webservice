package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.platrykp.cubeformservice.models.User;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
}


