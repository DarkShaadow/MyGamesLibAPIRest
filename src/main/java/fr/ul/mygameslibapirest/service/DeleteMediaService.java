package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.constante.MyMediaType;
import fr.ul.mygameslibapirest.entity.Editor;
import fr.ul.mygameslibapirest.entity.Game;
import fr.ul.mygameslibapirest.entity.Media;
import fr.ul.mygameslibapirest.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DeleteMediaService extends IService<Media, Long, MediaRepository> {

    @Value("${server.local-storage.picture}")
    private String pictureFolder;
    @Value("${server.local-storage.video}")
    private String videoFolder;

    private final GameService gameService;
    private final EditorService editorService;

    protected DeleteMediaService(MediaRepository repository,
                           GameService gameService,
                           EditorService editorService) {
        super(repository, "Media");
        this.gameService = gameService;
        this.editorService = editorService;
    }

    public String getPath(Media entity) {
        String fileName;
        String folderPath;

        folderPath = entity.getMediaType().equals(MyMediaType.VIDEO) ?
                videoFolder :
                pictureFolder;

        fileName = folderPath + "/" + entity.getId() + entity.getExtension();

        return fileName;
    }

    public boolean delete(Long id) {
        Media media;

        media = get(id);

        if (media.getMediaType() == MyMediaType.LOGO) {
            if (media.getEntityType() == EntityType.GAME) {
                Game game;

                game = gameService.get(media.getRelatedTo());
                game.setLogo(null);
                gameService.save(game);
            }
            else if (media.getEntityType() == EntityType.EDITOR) {
                Editor editor;

                editor = editorService.get(media.getRelatedTo());
                editor.setLogo(null);
                editorService.save(editor);
            }
        }
        repository.delete(media);

        return delete(media);
    }

    public boolean delete(Media media) {
        return new File(getPath(media)).delete();
    }
}
