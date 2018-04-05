package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Score;
import ee.ttu.studyhub.entity.ScoreDTO;
import ee.ttu.studyhub.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    private ScoreService scoreService;

    @Autowired
    private SubjectPersonController subjectPersonController;

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
}
