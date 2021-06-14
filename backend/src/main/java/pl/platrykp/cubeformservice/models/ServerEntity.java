package pl.platrykp.cubeformservice.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "servers")
@Data
@EqualsAndHashCode(of = {"id"})
public class ServerEntity {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private UUID id;

    @Column
    @Size(max = 20)
    private String name;

    @Column
    @Size(max = 10)
    private String gameMode;

    @Column
    private boolean mods;

    @ManyToOne
    private UserEntity owner;

    @OneToOne(cascade = CascadeType.ALL)
    private ServerAccessTokenEntity accessToken;

    public ServerEntity() {
    }

    public ServerEntity(String name, String gameMode, boolean mods, UserEntity owner, ServerAccessTokenEntity accessToken) {
        this.name = name;
        this.gameMode = gameMode;
        this.mods = mods;
        this.owner = owner;
        this.accessToken = accessToken;
    }
}
