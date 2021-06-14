package pl.platrykp.cubeformservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.platrykp.cubeformservice.models.ServerEntity;
import pl.platrykp.cubeformservice.models.UserEntity;

import java.util.UUID;

@Data
public class OfflineServerResource {
    private final UUID id;

    private String token;
    private String name;
    private String gameMode;
    private boolean mods;

    @JsonIgnore
    private UserEntity owner;

    public OfflineServerResource(ServerEntity serverEntity) {
        this.id = serverEntity.getId();
        this.token = serverEntity.getAccessToken().getAccessToken();
        this.name = serverEntity.getName();
        this.gameMode = serverEntity.getGameMode();
        this.mods = serverEntity.isMods();
        this.owner = serverEntity.getOwner();
    }

    public OfflineServerResource(UUID id, String token, String name, String gameMode, boolean mods, UserEntity owner) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.gameMode = gameMode;
        this.mods = mods;
        this.owner = owner;
    }

    @JsonProperty("owner")
    public UserOtherResource getOwner() {
        return new UserOtherResource(owner);
    }
}
