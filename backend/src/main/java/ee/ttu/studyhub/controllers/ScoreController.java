package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.*;
import ee.ttu.studyhub.service.PersonService;
import ee.ttu.studyhub.service.ScoreService;
import ee.ttu.studyhub.service.SubjectPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    private ScoreService scoreService;

    @Autowired
    private SubjectPersonService subjectPersonService;

    @Autowired
    private PersonService personService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(value="/scores/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Score addScore(@RequestBody ScoreDTO scoreDTO) {
        Score score = scoreService.findByPersonAndHeader(scoreDTO.getSubjectPerson(), scoreDTO.getHeader());
        if (score == null) {
            score = new Score();
            score.setSubjectPerson(scoreDTO.getSubjectPerson());
            score.setHeader(scoreDTO.getHeader());
        }

        score.setScore(scoreDTO.getScore());
        return scoreService.addScore(score);
    }

    @RequestMapping(value="/scores", method=RequestMethod.GET)
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    @RequestMapping(value = "/scores/{id}", method=RequestMethod.GET)
    public Score getScore(@PathVariable("id") long scoreId) {
        return scoreService.getScoreById(scoreId);
    }

    @DeleteMapping(value = "/scores/{id}")
    public List<Score> removeScore(@PathVariable("id") long scoreId) {
        scoreService.removeScore(scoreId);
        return getAllScores();
    }

    @GetMapping(value = "/latest-scores/{id}")
    public List<Score> getLatestScoresForSubjectPerson(@PathVariable("id") long personId) {
        Person person = personService.getPersonById(personId);
        List<SubjectPerson> subjectPersons = subjectPersonService.getSubjectPersonsByPerson(person);

        return scoreService.findLatestScoresForSubjectPerson(subjectPersons);
    }
}
