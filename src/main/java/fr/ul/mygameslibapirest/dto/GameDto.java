package fr.ul.mygameslibapirest.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Long id;

    private String name;
    private String description;
    private Date releaseDate;
    private Long editorId;
}
