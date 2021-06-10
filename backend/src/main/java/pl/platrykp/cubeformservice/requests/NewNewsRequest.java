package pl.platrykp.cubeformservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.platrykp.cubeformservice.models.NewsEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewNewsRequest {

    private String title;
    private String content;
    private long date;

    public NewsEntity newsEntity(){
        return new NewsEntity(title, content, new Timestamp(date));
    }
}
