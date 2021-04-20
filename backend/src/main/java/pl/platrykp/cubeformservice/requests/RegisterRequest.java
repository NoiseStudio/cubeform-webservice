package pl.platrykp.cubeformservice.requests;

public class RegisterRequest {

    public String login;
    public String password;
    public String email;

    public RegisterRequest() {
    }

    public RegisterRequest(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
