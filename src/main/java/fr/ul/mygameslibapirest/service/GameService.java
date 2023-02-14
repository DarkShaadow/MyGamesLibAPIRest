package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.entity.Game;
import fr.ul.mygameslibapirest.repository.GameRepository;

public class GameService extends IService<Game, Long, GameRepository> {

    protected GameService(GameRepository repository) {
        super(repository);
    }
}
