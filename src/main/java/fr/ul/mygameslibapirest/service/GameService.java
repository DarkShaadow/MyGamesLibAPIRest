package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.dto.requestbody.game.CreateGameBody;
import fr.ul.mygameslibapirest.dto.requestbody.game.PatchGameBody;
import fr.ul.mygameslibapirest.entity.Game;
import fr.ul.mygameslibapirest.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService extends IService<Game, Long, GameRepository> {

    private final EditorService editorService;

    protected GameService(GameRepository repository,
                          EditorService editorService) {
        super(repository, "Jeu");
        this.editorService = editorService;
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
}
