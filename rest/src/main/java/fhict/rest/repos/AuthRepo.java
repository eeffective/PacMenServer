package fhict.rest.repos;

import fhict.rest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AuthRepo extends JpaRepository<User, Long > {
    User findUserByName(String name);
    User findUserByNameAndPassword(String name, String password);
}
