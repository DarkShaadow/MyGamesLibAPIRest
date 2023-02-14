package fr.ul.mygameslibapirest.dto.requestbody.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class IGameBody {

    private String name;
    private String description;
    private Date releaseDate;
    private Long editorId;
}
