package ee.ttu.studyhub.service;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.entity.SubjectPerson;
import ee.ttu.studyhub.repository.SubjectPersonRepository;
import ee.ttu.studyhub.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectPersonService {

    @Autowired
    private SubjectPersonRepository subjectPersonRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectPersonService(SubjectPersonRepository subjectPersonRepository) {
        this.subjectPersonRepository = subjectPersonRepository;
    }

    public SubjectPerson addSubjectPerson(SubjectPerson subjectPerson) {
        return subjectPersonRepository.save(subjectPerson);
    }

    public List<SubjectPerson> getAllSubjectPersons() {
        return subjectPersonRepository.findAll();
    }

    public SubjectPerson getSubjectPersonById(long subjectPersonId) {
        return subjectPersonRepository.findOne(subjectPersonId);
    }

    public List<SubjectPerson> getSubjectPersonsBySubjectId(Long subjectId) {
        Subject subject = subjectRepository.findOne(subjectId);
        return subjectPersonRepository.findAllBySubject(subject);
    }

    public List<SubjectPerson> getSubjectPersonsByPerson(Person person) {
        return subjectPersonRepository.findAllByPerson(person);
    }
}

