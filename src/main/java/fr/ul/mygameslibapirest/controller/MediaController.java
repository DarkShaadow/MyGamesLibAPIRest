package fr.ul.mygameslibapirest.controller;

import fr.ul.mygameslibapirest.service.MediaService;
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

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping(path = "/{id}/download")
    public ResponseEntity<Resource> download(@Parameter @PathVariable Long id) throws IOException {
        return mediaService.download(id);
    }
}
