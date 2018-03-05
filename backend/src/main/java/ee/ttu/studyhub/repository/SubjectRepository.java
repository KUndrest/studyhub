package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    @Override
    public List<Subject> findAll();
}
