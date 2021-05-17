package pl.platrykp.cubeformservice.requests;

import pl.platrykp.cubeformservice.models.News;
import pl.platrykp.cubeformservice.models.NewsEntity;

import java.sql.Timestamp;

public class NewNewsRequest {

    public String title;
    public String content;
    public long date;

    public NewNewsRequest(){}

    public NewNewsRequest(String title, String content, long date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public News newsEntity(){
        return new News(title, content, new Timestamp(date));
    }
}
