package com.fhict.pacmenrest.services;

import com.fhict.pacmenrest.models.Score;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    public void saveScore(Score score){
        System.out.println(score.getName() + ", " + score.getScore());
    }
}
