package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Override
    public List<Subject> findAll();

    public List<Subject> findBySubject(String searchString);
}
