package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.service.SubjectService;
import ee.ttu.studyhub.entity.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value="/subjects/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @RequestMapping(value="/subjects", method=RequestMethod.GET)
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @RequestMapping(value = "/subjects/{id}", method=RequestMethod.GET)
    public Subject getSubject(@PathVariable("id") long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @DeleteMapping(value = "/subjects/{id}")
    public List<Subject> removeSubject(@PathVariable("id") long subjectId) {
        subjectService.removeSubject(subjectId);
        return getAllSubjects();
    }

}
