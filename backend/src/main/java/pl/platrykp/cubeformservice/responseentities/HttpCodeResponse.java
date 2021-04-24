package pl.platrykp.cubeformservice.responseentities;

public class HttpCodeResponse {

    public long time;
    public int code;
    public String message;
    public String method;
    public String path;

    public HttpCodeResponse(int code, String message, String method, String path) {
        this.time = System.currentTimeMillis();
        this.code = code;
        this.message = message;
        this.method = method;
        this.path = path;
    }
}
