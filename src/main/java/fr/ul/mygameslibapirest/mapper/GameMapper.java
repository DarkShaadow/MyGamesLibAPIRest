package fr.ul.mygameslibapirest.mapper;

import fr.ul.mygameslibapirest.dto.GameDto;
import fr.ul.mygameslibapirest.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mappings({
            @Mapping(source = "editor.id", target = "editorId")
    })
    GameDto to(Game entity);
    Game to(GameDto dto);
}
