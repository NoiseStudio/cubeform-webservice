package pl.platrykp.cubeformservice.resources;

import pl.platrykp.cubeformservice.models.NewsEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MultipleNewsResource {

    public List<NewsResource> newsEntities;

    public MultipleNewsResource(List<NewsEntity> newsEntityList){
        newsEntities = newsEntityList.stream()
                .map(NewsResource::new)
                .collect(Collectors.toList());
    }
}
