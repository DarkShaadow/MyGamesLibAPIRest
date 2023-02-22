package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.constante.MediaType;
import fr.ul.mygameslibapirest.entity.Media;
import fr.ul.mygameslibapirest.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MediaService extends IService<Media, Long, MediaRepository> {

    @Value("${server.local-storage.picture}")
    private String pictureFolder;
    @Value("${server.local-storage.video}")
    private String videoFolder;

    protected MediaService(MediaRepository repository) {
        super(repository, "Media");
    }

    public Media upload(Long idEntity, EntityType entityType, MultipartFile file, MediaType mediaType) throws IOException {
        String extension;
        Media media;

        extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        media = Media.builder()
                .entityType(entityType)
                .mediaType(mediaType)
                .extension(extension)
                .relatedTo(idEntity)
                .build();

        media = super.save(media);

        file.transferTo(new File(getPath(media)));

        return media;
    }
    public Media uploadLogo(Long idEntity, EntityType entityType, MultipartFile file) throws IOException {
        String extension;
        Media media;

        media = repository.getMediaByRelatedToAndEntityTypeAndMediaType(idEntity, entityType, MediaType.LOGO)
                .stream()
                .findFirst()
                .orElse(null);
        if (media != null) {
            new File(getPath(media)).delete();
        }
        else {
            extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

            media = Media.builder()
                    .entityType(entityType)
                    .mediaType(MediaType.LOGO)
                    .extension(extension)
                    .relatedTo(idEntity)
                    .build();

            media = super.save(media);
        }

        file.transferTo(new File(getPath(media)));

        return media;
    }

    public String getPath(Media entity) {
        String fileName;
        String folderPath;

        folderPath = entity.getMediaType().equals(MediaType.VIDEO) ?
                videoFolder :
                pictureFolder;

        fileName = folderPath + "/" + entity.getId() + entity.getExtension();

        return fileName;
    }
}
