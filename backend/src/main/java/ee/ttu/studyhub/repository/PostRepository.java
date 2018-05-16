package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.entity.Post;
import ee.ttu.studyhub.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubject(Subject subject);

    @Query("select p from Post p join p.subject s join s.subjectPersons sps where sps.person = :subjectPerson")
    List<Post> findBySubjectPerson(@Param("subjectPerson") Person subjectPerson);
}
