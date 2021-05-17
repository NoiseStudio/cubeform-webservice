package pl.platrykp.cubeformservice.resources;

import net.minidev.json.annotate.JsonIgnore;
import pl.platrykp.cubeformservice.models.News;

public class NewsResource {

    @JsonIgnore
    private final News news;

    public NewsResource(News data){
        this.news = data;
    }


    public int getId() {
        return news.getId();
    }

    public String getTitle() {
        return news.getTitle();
    }

    public String getContent() {
        return news.getContent();
    }

    public long getDate() {
        return news.getDate().getTime();
    }
}
