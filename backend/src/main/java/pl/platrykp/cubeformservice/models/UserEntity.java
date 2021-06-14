package pl.platrykp.cubeformservice.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name= "users")
@EqualsAndHashCode(of = {"id"})
public class UserEntity {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    @Getter @Setter
    private UUID id;

    @ManyToOne
    @Getter @Setter
    private RoleEntity role;

    @Column
    @Getter @Setter
    private String username;

    @Column
    @Getter @Setter
    private String email;

    @Column
    @Getter @Setter
    private String password;

    @Column
    @Getter @Setter
    private Timestamp creationDate;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Getter @Setter
    private Set<ServerEntity> servers;

    public UserEntity(){}

    public UserEntity(String username, String email, String password, Timestamp creationDate, RoleEntity role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.creationDate = creationDate;
    }

    public UserEntity(String username, String email, String password, RoleEntity role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }



}
