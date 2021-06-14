package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.platrykp.cubeformservice.models.ServerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServerRepository extends JpaRepository<ServerEntity, UUID> {

    Optional<ServerEntity> findByAccessToken_AccessToken(String accessToken);

    List<ServerEntity> findByOwner_Id(UUID ownerId);
}
