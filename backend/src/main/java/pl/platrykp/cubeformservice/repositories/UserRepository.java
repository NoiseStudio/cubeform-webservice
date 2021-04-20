package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.platrykp.cubeformservice.models.User;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(int id);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);

    Optional<User> findByLoginOrEmail(String login, String email);
}


