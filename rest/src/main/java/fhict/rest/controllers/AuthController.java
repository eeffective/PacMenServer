package fhict.rest.controllers;

import fhict.rest.models.User;
import fhict.rest.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
    public void Register(@Valid @RequestBody User user){
        try {
            if (!authService.NameAlreadyRegistered(user)){
                authService.RegisterUser(user);
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
    public User Login(@RequestBody User user){
        String tempEmail = user.getName();
        String tempPw = user.getPassword();
        User loginUser = null;
        if (tempEmail != null && tempPw != null){
            loginUser = authService.GetUserByNameAndPassword(tempEmail, tempPw);
        }
        if (loginUser == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return loginUser;
    }
}
