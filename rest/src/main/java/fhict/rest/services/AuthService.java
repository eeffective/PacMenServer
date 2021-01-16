package fhict.rest.services;

import fhict.rest.models.User;
import fhict.rest.repos.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepo aRepo;

    public void RegisterUser(User user){ aRepo.save(user); }

    public boolean NameAlreadyRegistered(User user) {
        if (aRepo.findUserByName(user.getName()) != null){
            return true;
        }
        return false;
    }

    public User GetUserByNameAndPassword(String name, String password){
        return aRepo.findUserByNameAndPassword(name, password);
    }
}
