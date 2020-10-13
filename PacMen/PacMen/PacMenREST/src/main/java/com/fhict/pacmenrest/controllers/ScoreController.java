package com.fhict.pacmenrest.controllers;

import com.fhict.pacmenrest.models.Score;
import com.fhict.pacmenrest.services.ScoreService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    private final ScoreService service;

    public ScoreController(ScoreService scoreService){
        service = scoreService;
    }

    @PostMapping(value = "/score", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addScore(@RequestBody Score score){
        service.saveScore(score);
    }
}
