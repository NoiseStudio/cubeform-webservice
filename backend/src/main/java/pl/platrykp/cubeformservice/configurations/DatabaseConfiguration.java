package pl.platrykp.cubeformservice.configurations;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() throws IOException, JSONException, SQLException {
        File databaseFile = ResourceUtils.getFile("classpath:databaseConnectionConfig.json");
        JSONObject json = new JSONObject(Files.readString(databaseFile.toPath()));

        DataSource dataSource =
                DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(      json.getString("url")      )
                .username( json.getString("username") )
                .password( json.getString("password") )
                .build();

        return dataSource;
    }
}
