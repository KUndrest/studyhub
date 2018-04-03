package ee.ttu.studyhub.service;

import ee.ttu.studyhub.entity.SubjectPerson;
import ee.ttu.studyhub.repository.SubjectPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectPersonService {

    @Autowired
    private SubjectPersonRepository subjectPersonRepository;

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
}

