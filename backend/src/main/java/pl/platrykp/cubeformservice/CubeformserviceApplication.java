package pl.platrykp.cubeformservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CubeformserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CubeformserviceApplication.class, args);
    }
}
