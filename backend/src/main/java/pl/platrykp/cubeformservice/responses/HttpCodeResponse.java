package pl.platrykp.cubeformservice.responses;


import lombok.Data;

@Data
public class HttpCodeResponse {

    private long time;
    private int code;
    private String message;
    private String method;
    private String path;


    public HttpCodeResponse() {
    }

    public HttpCodeResponse(int code, String message, String method, String path) {
        this.time = System.currentTimeMillis();
        this.code = code;
        this.message = message;
        this.method = method;
        this.path = path;
    }
}
