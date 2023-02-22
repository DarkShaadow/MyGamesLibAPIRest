package fr.ul.mygameslibapirest.controller;

import fr.ul.mygameslibapirest.service.DeleteMediaService;
import fr.ul.mygameslibapirest.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(maxAge = 3600)
@Tag(name = "Media")
@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;
    private final DeleteMediaService deleteMediaService;

    public MediaController(MediaService mediaService,
                           DeleteMediaService deleteMediaService) {
        this.mediaService = mediaService;
        this.deleteMediaService = deleteMediaService;
    }

    @Operation(summary = "Télécharger un média")
    @GetMapping(path = "/{id}/download")
    public ResponseEntity<Resource> download(@Parameter @PathVariable Long id) throws IOException {
        return mediaService.download(id);
    }

    @Operation(summary = "Supprimer un média")
    @DeleteMapping(path = "/{id}")
    public boolean delete(@Parameter @PathVariable Long id) {
        return deleteMediaService.delete(id);
    }
}
