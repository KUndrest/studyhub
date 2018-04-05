package ee.ttu.studyhub.service;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.entity.Score;
import ee.ttu.studyhub.entity.SubjectPerson;
import ee.ttu.studyhub.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score addScore(Score score) {
        return scoreRepository.save(score);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Score getScoreById(long scoreId) {
        return scoreRepository.findOne(scoreId);
    }

    public Score findByPersonAndHeader(SubjectPerson person, Header header) {
        return scoreRepository.findFirstBySubjectPersonAndHeader(person, header);
    }
}
