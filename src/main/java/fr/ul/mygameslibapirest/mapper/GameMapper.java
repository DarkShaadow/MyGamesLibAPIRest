package fr.ul.mygameslibapirest.mapper;

import fr.ul.mygameslibapirest.dto.GameDto;
import fr.ul.mygameslibapirest.entity.Game;

public interface GameMapper {

    GameDto to(Game entity);
    Game to(GameDto dto);
}
