package pl.platrykp.cubeformservice.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
@EqualsAndHashCode(of = {"id"})
public class RoleEntity {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false, unique = true, updatable = false)
    @Getter @Setter
    private UUID id;

    @Column
    @Getter @Setter
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }
}
