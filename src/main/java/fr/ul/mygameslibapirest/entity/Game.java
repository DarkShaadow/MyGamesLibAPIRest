package fr.ul.mygameslibapirest.entity;

import fr.ul.mygameslibapirest.constante.EntityType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GAME")
public class Game implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date releaseDate;

    @ManyToOne
    private Editor editor;

    @OneToOne
    private Media logo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "related_to", referencedColumnName = "id")
    @Where(clause = "entity_type = '0' AND media_type != '0'")
    private List<Media> medias = new ArrayList<>();

    private EntityType entityType = EntityType.GAME;

    public void addMedia(Media media) {
        this.medias.add(media);
    }
}
