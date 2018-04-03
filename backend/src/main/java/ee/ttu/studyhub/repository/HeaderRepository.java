package ee.ttu.studyhub.repository;

import ee.ttu.studyhub.entity.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Long> {

}
