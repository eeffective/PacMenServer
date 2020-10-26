package fhict.rest.services;


import fhict.rest.models.Score;
import fhict.rest.repos.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    public ScoreRepository scoreRepository;

    public void saveScore(Score score){
        scoreRepository.save(score);
    }
}
