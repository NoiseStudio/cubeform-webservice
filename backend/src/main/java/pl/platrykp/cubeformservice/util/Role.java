package pl.platrykp.cubeformservice.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.platrykp.cubeformservice.models.RoleEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Role {

    UNAUTHORIZED(0, "unauthorized"),
    USER(1, "user"), ADMIN(128, "admin");

    private static final Logger logger = LoggerFactory.getLogger(Role.class);

    private final int id;
    private final String name;

    private Role(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Role fromId(int id){
        switch (id) {
            case 1: return USER;
            case 2: return ADMIN;
            default:
                String stack = Arrays.stream(Thread.currentThread().getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining("\n"));
                logger.warn("Accessed 'UNAUTHORIZED' role (id: {}). Stack: {}", id, stack);
                return UNAUTHORIZED;
        }
    }

    public static Role fromName(String name){
        switch (name.toLowerCase()) {
            case "user": return USER;
            case "admin": return ADMIN;
            default:
                String stack = Arrays.stream(Thread.currentThread().getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining("\n"));
                logger.warn("Accessed 'UNAUTHORIZED' role (name: {}). Stack: {}", name, stack);
                return UNAUTHORIZED;
        }
    }

    public static Role fromRoleEntity(RoleEntity entity){
        return fromId(entity.getId());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
