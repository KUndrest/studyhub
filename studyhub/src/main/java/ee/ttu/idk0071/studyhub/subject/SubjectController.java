package ee.ttu.idk0071.studyhub.subject;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value="/subjects/add", method=RequestMethod.POST,
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
}