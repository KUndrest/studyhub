package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.service.SubjectPersonService;
import ee.ttu.studyhub.entity.SubjectPerson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectPersonController {

    private SubjectPersonService subjectPersonService;

    public SubjectPersonController(SubjectPersonService subjectPersonService) {
        this.subjectPersonService = subjectPersonService;
    }

    @RequestMapping(value="/subjectPersons/add", method= RequestMethod.POST,
            consumes = "application/json")
    public SubjectPerson addSubjectPerson(@RequestBody SubjectPerson subjectPerson) {
        return subjectPersonService.addSubjectPerson(subjectPerson);
    }

    @GetMapping(value="/subjectPersons")
    public List<SubjectPerson> getAllSubjectPersons() {
        return subjectPersonService.getAllSubjectPersons();
    }

    @RequestMapping(value = "/subjectPersons/{id}", method=RequestMethod.GET)
    public SubjectPerson getSubjectPerson(@PathVariable("id") long subjectPersonId) {
        return subjectPersonService.getSubjectPersonById(subjectPersonId);
    }
}
