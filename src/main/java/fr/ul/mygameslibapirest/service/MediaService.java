package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.constante.ErrorMessageConstante;
import fr.ul.mygameslibapirest.constante.MyMediaType;
import fr.ul.mygameslibapirest.entity.Media;
import fr.ul.mygameslibapirest.exception.FunctionalException;
import fr.ul.mygameslibapirest.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaService extends IService<Media, Long, MediaRepository> {

    @Value("${server.local-storage.picture}")
    private String pictureFolder;
    @Value("${server.local-storage.video}")
    private String videoFolder;

    protected MediaService(MediaRepository repository) {
        super(repository, "Media");
    }

    public Media upload(Long idEntity, EntityType entityType, MultipartFile file, MyMediaType myMediaType) throws IOException {
        String extension;
        Media media;

        extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        media = Media.builder()
                .entityType(entityType)
                .mediaType(myMediaType)
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

        media = repository.getMediaByRelatedToAndEntityTypeAndMediaType(idEntity, entityType, MyMediaType.LOGO)
                .stream()
                .findFirst()
                .orElse(null);
        if (media != null) {
            delete(media);
        }
        else {
            extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

            media = Media.builder()
                    .entityType(entityType)
                    .mediaType(MyMediaType.LOGO)
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

        folderPath = entity.getMediaType().equals(MyMediaType.VIDEO) ?
                videoFolder :
                pictureFolder;

        fileName = folderPath + "/" + entity.getId() + entity.getExtension();

        return fileName;
    }

    public ResponseEntity<Resource> download(Long id) throws IOException {
        HttpHeaders headers;
        Media media;
        File file;
        Path path;

        media = get(id);
        file = new File(getPath(media));

        headers = new HttpHeaders();
        headers.add("File-Name", String.valueOf(media.getId()));
        headers.add("File-Extension", media.getExtension());

        path = Paths.get(file.getAbsolutePath());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(Files.readAllBytes(path)));
    }

    public boolean delete(Long id) {
        Media media;

        media = get(id);

        if (media.getMediaType() == MyMediaType.LOGO) {
            throw new FunctionalException(ErrorMessageConstante.DELETE_LOGO);
        }

        repository.delete(media);

        return delete(media);
    }

    public boolean delete(Media media) {
        return new File(getPath(media)).delete();
    }
}
