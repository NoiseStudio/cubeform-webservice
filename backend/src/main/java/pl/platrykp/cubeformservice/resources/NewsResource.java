package pl.platrykp.cubeformservice.resources;

import net.minidev.json.annotate.JsonIgnore;
import pl.platrykp.cubeformservice.models.NewsEntity;

import java.util.UUID;

public class NewsResource {

    @JsonIgnore
    private final NewsEntity newsEntity;

    public NewsResource(NewsEntity data){
        this.newsEntity = data;
    }


    public UUID getId() {
        return newsEntity.getId();
    }

    public String getTitle() {
        return newsEntity.getTitle();
    }

    public String getContent() {
        return newsEntity.getContent();
    }

    public long getDate() {
        return newsEntity.getDate().getTime();
    }
}
