package fhict.rest.repos;

import fhict.rest.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<Player, Long > {
    Player findPlayerByUsername(String name);
    Player findPlayerByUsernameAndPassword(String name, String password);
}
