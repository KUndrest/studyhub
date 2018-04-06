package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Post;
import ee.ttu.studyhub.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubject(Subject subject);
}
