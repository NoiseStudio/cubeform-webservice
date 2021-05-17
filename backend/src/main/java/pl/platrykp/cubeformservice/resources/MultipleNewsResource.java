package pl.platrykp.cubeformservice.resources;

import pl.platrykp.cubeformservice.models.News;
import pl.platrykp.cubeformservice.models.NewsEntity;

import java.util.List;

public class MultipleNewsResource {

    public List<News> news;

    public MultipleNewsResource(List<News> newsList){
        news = newsList;
    }
}
