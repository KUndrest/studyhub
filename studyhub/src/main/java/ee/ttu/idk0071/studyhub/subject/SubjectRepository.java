package ee.ttu.idk0071.studyhub.subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long>{
    @Override
    public List<Subject> findAll();
}