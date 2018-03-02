package ee.ttu.idk0071.studyhub.subject;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    Subject addSubject(Subject subject) {
        return subjectRepository.save(user);
    }

    List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    Subject getSubjectById(long subjectId) {
        return subjectRepository.findOne(subjectId);
    }
}