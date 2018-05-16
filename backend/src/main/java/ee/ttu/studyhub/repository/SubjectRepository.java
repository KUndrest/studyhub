package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Override
    public List<Subject> findAll();

    public List<Subject> findBySubject(String searchString);

    @Query("select s from Subject s join s.subjectPersons sps where sps.person = :subjectPerson")
    List<Subject> findAllBySubjectPerson(@Param("subjectPerson") Person subjectPerson);

    @Query("select s from Subject s where s.person = :person")
    List<Subject> findAllByPerson(@Param("person") Person person);
}
