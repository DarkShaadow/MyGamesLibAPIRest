package fr.ul.mygameslibapirest.repository;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.constante.MediaType;
import fr.ul.mygameslibapirest.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> getMediaByRelatedToAndEntityTypeAndMediaType(Long relatedTo, EntityType entityType, MediaType mediaType);
}
