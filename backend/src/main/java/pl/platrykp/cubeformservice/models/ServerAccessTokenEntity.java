package pl.platrykp.cubeformservice.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "server_access_token")
@Data
@EqualsAndHashCode(of = {"id"})
public class ServerAccessTokenEntity {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private UUID id;

    @Column
    private String accessToken;

    @Column
    private Timestamp creationTime;

    @OneToOne(mappedBy = "accessToken")
    private ServerEntity server;

    public ServerAccessTokenEntity() {
    }

    public ServerAccessTokenEntity(String accessToken, Timestamp creationTime, ServerEntity server) {
        this.accessToken = accessToken;
        this.creationTime = creationTime;
        this.server = server;
    }
}
