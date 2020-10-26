package fhict.rest.controllers;

import fhict.rest.models.Score;
import fhict.rest.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ScoreController {
    @Autowired
    private final ScoreService service;

    public ScoreController(ScoreService scoreService){
        service = scoreService;
    }

    @PostMapping(value = "/score")
    public void addScore(@Valid @RequestBody Score score){
        service.saveScore(score);
    }
}
