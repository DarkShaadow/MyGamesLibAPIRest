package fr.ul.mygameslibapirest.repository;

import fr.ul.mygameslibapirest.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
