package ee.ttu.studyhub.service;

import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(long subjectId) {
        return subjectRepository.findOne(subjectId);
    }

    public void removeSubject(long subjectId) {
        subjectRepository.delete(subjectId);
    }

    public List<Subject> searchSubjectsByName(String searchString) { return subjectRepository.findBySubject(searchString); }
}
