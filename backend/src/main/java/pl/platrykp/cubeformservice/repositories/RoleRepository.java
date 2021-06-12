package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.platrykp.cubeformservice.models.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String name);
}
