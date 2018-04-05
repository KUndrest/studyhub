package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Long> {
    List<Header> findAllBySubject(Subject subject);
}
