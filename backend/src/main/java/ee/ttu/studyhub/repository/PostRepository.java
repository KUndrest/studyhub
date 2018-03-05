package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Override
    public List<Post> findAll();
}
