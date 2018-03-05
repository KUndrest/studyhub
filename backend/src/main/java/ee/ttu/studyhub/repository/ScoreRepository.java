package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    @Override
    public List<Score> findAll();
}
