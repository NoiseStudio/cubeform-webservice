package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.platrykp.cubeformservice.models.UserEntity;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}


