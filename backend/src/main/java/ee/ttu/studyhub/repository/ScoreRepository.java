package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.entity.Score;
import ee.ttu.studyhub.entity.SubjectPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findFirstBySubjectPersonAndHeader(SubjectPerson person, Header header);
}
