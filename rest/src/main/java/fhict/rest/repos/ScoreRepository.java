package fhict.rest.repos;

import fhict.rest.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ScoreRepository extends JpaRepository<Score, Long> {

}
