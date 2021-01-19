package fhict.rest.controllers;

import fhict.rest.models.Player;
import fhict.rest.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping(value = "register")
    public void Register(@Valid @RequestBody Player player){
        try {
            if (!authService.NameAlreadyRegistered(player)){
                authService.RegisterUser(player);
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "login")
    public Player Login(@RequestBody Player player){
        String tempEmail = player.getUsername();
        String tempPw = player.getPassword();
        Player loginPlayer = null;
        if (tempEmail != null && tempPw != null){
            loginPlayer = authService.GetUserByNameAndPassword(tempEmail, tempPw);
        }
        if (loginPlayer == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return loginPlayer;
    }
}
