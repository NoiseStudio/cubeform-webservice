package pl.platrykp.cubeformservice.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.platrykp.cubeformservice.models.NewsEntity;
import pl.platrykp.cubeformservice.repositories.NewsRepository;
import pl.platrykp.cubeformservice.requests.NewNewsRequest;
import pl.platrykp.cubeformservice.resources.MultipleNewsResource;
import pl.platrykp.cubeformservice.resources.NewsResource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/{id}")
    public NewsResource getNews(@PathVariable UUID id) throws NotFoundException {
        Optional<NewsEntity> news = newsRepository.findById(id);
        if(news.isPresent())
            return new NewsResource(news.get());

        throw new ResourceNotFoundException();
    }

    @GetMapping("/")
    public MultipleNewsResource getNews() {
        List<NewsEntity> newsEntities = newsRepository.findTop10ByOrderByDate();

        return new MultipleNewsResource(newsEntities);
    }


    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> newNews(@RequestBody NewNewsRequest newsRequest){
        NewsEntity newNewsEntityEntity = newsRequest.newsEntity();

        newsRepository.save(newNewsEntityEntity);
        return ResponseEntity.ok("");
    }

}
