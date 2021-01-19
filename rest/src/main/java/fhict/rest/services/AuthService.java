package fhict.rest.services;

import fhict.rest.models.Player;
import fhict.rest.repos.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepo aRepo;

    public void RegisterUser(Player player){ aRepo.save(player); }

    public boolean NameAlreadyRegistered(Player player) {
        if (aRepo.findPlayerByUsername(player.getUsername()) != null){
            return true;
        }
        return false;
    }

    public Player GetUserByNameAndPassword(String name, String password){
        return aRepo.findPlayerByUsernameAndPassword(name, password);
    }
}
