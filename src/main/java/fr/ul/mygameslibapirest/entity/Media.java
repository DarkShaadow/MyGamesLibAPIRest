package fr.ul.mygameslibapirest.entity;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.constante.MyMediaType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEDIA")
public class Media implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private EntityType entityType;
    private MyMediaType mediaType;
    private String extension;

    @Column(name = "related_to")
    private Long relatedTo;
}
