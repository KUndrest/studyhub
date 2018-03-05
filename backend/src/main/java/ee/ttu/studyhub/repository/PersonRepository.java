package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Override
    public List<Person> findAll();
}
