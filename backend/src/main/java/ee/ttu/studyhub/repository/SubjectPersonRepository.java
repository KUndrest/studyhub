package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectPersonRepository extends JpaRepository<SubjectPerson, Long> {
    List<SubjectPerson> findAllBySubject(Subject subject);
}
