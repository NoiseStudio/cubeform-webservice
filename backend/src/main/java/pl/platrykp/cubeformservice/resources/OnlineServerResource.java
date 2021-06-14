package pl.platrykp.cubeformservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.platrykp.cubeformservice.models.UserEntity;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = {"id"})
public class OnlineServerResource {

    private final UUID id;

    // access token should never be send to another user
    @JsonIgnore
    private final String accessToken;
    private final String address;
    private final int port;

    private String name;
    private String gameMode;
    private boolean mods;

    @JsonIgnore
    private UserEntity owner;

    private int maxPlayers;
    private int currentPlayers;
    private int ping;


    public OnlineServerResource(UUID id, String accessToken, String address, int port, String name, String gameMode,
                                boolean mods, UserEntity owner) {
        this.id = id;
        this.accessToken = accessToken;
        this.address = address;
        this.port = port;
        this.name = name;
        this.gameMode = gameMode;
        this.mods = mods;
        this.owner = owner;
        this.maxPlayers = 0;
        this.currentPlayers = 0;
        this.ping = 999;
    }

    @JsonProperty("owner")
    public UserOtherResource getOwner() {
        return new UserOtherResource(owner);
    }

}
