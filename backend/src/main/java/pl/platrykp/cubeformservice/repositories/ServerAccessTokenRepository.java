package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.platrykp.cubeformservice.models.ServerAccessTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface ServerAccessTokenRepository extends JpaRepository<ServerAccessTokenEntity, UUID> {
    Optional<ServerAccessTokenEntity> findByServer_Id(UUID serverId);
    Optional<ServerAccessTokenEntity> findByAccessToken(String accessToken);
}
