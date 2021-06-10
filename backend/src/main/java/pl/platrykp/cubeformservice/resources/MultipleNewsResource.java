package pl.platrykp.cubeformservice.resources;

import pl.platrykp.cubeformservice.models.NewsEntity;

import java.util.List;

public class MultipleNewsResource {

    public List<NewsEntity> newsEntities;

    public MultipleNewsResource(List<NewsEntity> newsEntityList){
        newsEntities = newsEntityList;
    }
}
