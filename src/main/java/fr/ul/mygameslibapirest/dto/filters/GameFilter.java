package fr.ul.mygameslibapirest.dto.filters;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameFilter {

    private String name;
    private Long editorId;
}
