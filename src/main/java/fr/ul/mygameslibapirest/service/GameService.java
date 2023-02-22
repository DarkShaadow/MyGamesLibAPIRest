package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.constante.MyMediaType;
import fr.ul.mygameslibapirest.dto.filters.GameFilter;
import fr.ul.mygameslibapirest.dto.requestbody.game.CreateGameBody;
import fr.ul.mygameslibapirest.dto.requestbody.game.PatchGameBody;
import fr.ul.mygameslibapirest.entity.Game;
import fr.ul.mygameslibapirest.entity.Media;
import fr.ul.mygameslibapirest.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService extends IService<Game, Long, GameRepository> {

    private final MediaService mediaService;
    private final EditorService editorService;

    protected GameService(GameRepository repository,
                          MediaService mediaService,
                          EditorService editorService) {
        super(repository, "Jeu");
        this.mediaService = mediaService;
        this.editorService = editorService;
    }

    public Page<Game> getAll(GameFilter filter, Pageable pageable) {
        return repository.findAll(filter, pageable);
    }

    public Game create(CreateGameBody body) {
        Game entity;

        entity = Game.builder()
                .name(body.getName())
                .description(body.getDescription())
                .releaseDate(body.getReleaseDate())
                .editor(editorService.get(body.getEditorId()))
                .build();

        return super.save(entity);
    }

    public Game update(Long id, PatchGameBody body) {
        Game entity;

        entity = get(id);
        entity.setName(body.getName() == null ? entity.getName() : body.getName());
        entity.setDescription(body.getDescription() == null ? entity.getDescription() : body.getDescription());
        entity.setReleaseDate(body.getReleaseDate() == null ? entity.getReleaseDate() : body.getReleaseDate());
        entity.setEditor(body.getEditorId() == null ? entity.getEditor() : editorService.get(body.getEditorId()));

        return super.save(entity);
    }

    @Transactional
    public Long uploadMedia(Long id, MultipartFile file, MyMediaType mediaType) throws IOException {
        if (mediaType == MyMediaType.LOGO) {
            return addLogo(id, file);
        }
        else {
            return addMedia(id, file, mediaType);
        }
    }

    public Long addLogo(Long id, MultipartFile file) throws IOException {
        Media media;
        Game entity;

        entity = get(id);
        media = mediaService.uploadLogo(id, EntityType.GAME, file);
        entity.setLogo(media);

        return media.getId();
    }

    public Long addMedia(Long id, MultipartFile file, MyMediaType mediaType) throws IOException {
        Media media;
        Game entity;

        entity = get(id);
        media = mediaService.upload(id, EntityType.GAME, file, mediaType);
        entity.addMedia(media);

        return media.getId();
    }

    public List<Long> getMedias(Long id, MyMediaType mediaType) {
        if (mediaType == MyMediaType.LOGO) {
            return Collections.singletonList(get(id).getLogo().getId());
        } else {
            return get(id).getMedias()
                    .stream()
                    .filter(media -> media.getMediaType() == mediaType)
                    .map(Media::getId)
                    .collect(Collectors.toList());
        }
    }
}
