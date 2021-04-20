package pl.platrykp.cubeformservice.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String login;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Timestamp creationDate;

    public User(){}

    public User(String login, String email, String password, Timestamp creationDate) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
