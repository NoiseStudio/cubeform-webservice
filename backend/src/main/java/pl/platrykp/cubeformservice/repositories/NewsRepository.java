package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.platrykp.cubeformservice.models.News;
import pl.platrykp.cubeformservice.models.NewsEntity;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends CrudRepository<News, Integer> {

    @Override
    Optional<News> findById(Integer id);

    List<News> findTop10ByOrderByDate();
}
