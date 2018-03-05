package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Score;
import ee.ttu.studyhub.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    private ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(value="/scores/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Score addScore(@RequestBody Score score) {
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
