package pl.platrykp.cubeformservice.responseentities;

import lombok.Data;

@Data
public class MessageResponse {

    private long time;
    private String message;

    public MessageResponse(String message) {
        this.time = System.currentTimeMillis();
        this.message = message;
    }
}
