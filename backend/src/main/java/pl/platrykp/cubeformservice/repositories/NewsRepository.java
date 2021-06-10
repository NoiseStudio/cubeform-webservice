package pl.platrykp.cubeformservice.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.platrykp.cubeformservice.models.NewsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsRepository extends CrudRepository<NewsEntity, UUID> {

    List<NewsEntity> findTop10ByOrderByDate();
}
