package ee.ttu.studyhub.repository;


import ee.ttu.studyhub.entity.SubjectPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectPersonRepository extends JpaRepository<SubjectPerson, Long> {

}
