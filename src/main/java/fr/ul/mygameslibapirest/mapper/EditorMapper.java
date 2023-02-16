package fr.ul.mygameslibapirest.mapper;

import fr.ul.mygameslibapirest.dto.EditorDto;
import fr.ul.mygameslibapirest.entity.Editor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditorMapper {

    EditorDto to(Editor entity);
    Editor to(EditorDto dto);
}
