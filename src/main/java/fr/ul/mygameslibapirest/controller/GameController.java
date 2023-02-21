package fr.ul.mygameslibapirest.controller;

import fr.ul.mygameslibapirest.constante.MediaType;
import fr.ul.mygameslibapirest.dto.GameDto;
import fr.ul.mygameslibapirest.dto.requestbody.game.CreateGameBody;
import fr.ul.mygameslibapirest.dto.requestbody.game.PatchGameBody;
import fr.ul.mygameslibapirest.mapper.GameMapper;
import fr.ul.mygameslibapirest.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@Tag(name = "Game")
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameMapper gameMapper;
    private final GameService gameService;

    public GameController(GameMapper gameMapper,
                            GameService gameService) {
        this.gameMapper = gameMapper;
        this.gameService = gameService;
    }

    @Operation(summary = "Retourne les jeux paginés")
    @GetMapping("")
    public List<GameDto> getAll() {
        return gameService.getAll()
                .stream()
                .map(gameMapper::to)
                .collect(Collectors.toList());
    }
    @Operation(summary = "Retourne un jeu")
    @GetMapping("/{id}")
    public GameDto get(@Parameter @PathVariable Long id) {
        return gameMapper.to(gameService.get(id));
    }
    @Operation(summary = "Retourne les médias d'un jeu")
    @GetMapping("/{id}/media")
    public List<String> getPictures(@Parameter @PathVariable Long id,
                                    @Parameter @RequestParam MediaType mediaType) {
        return gameService.getMedias(id, mediaType);
    }

    @Operation(summary = "Créer un jeu")
    @PostMapping("")
    public GameDto create(@RequestBody CreateGameBody body) {
        return gameMapper.to(gameService.create(body));
    }
    @Operation(summary = "Mettre à jour un jeu")
    @PatchMapping("/{id}")
    public GameDto update(@Parameter @PathVariable Long id,
                            @RequestBody PatchGameBody body) {
        return gameMapper.to(gameService.update(id, body));
    }
    @Operation(summary = "Supprimer un jeu")
    @DeleteMapping("/{id}")
    public boolean delete(@Parameter @PathVariable Long id) {
        return gameService.delete(id);
    }

    @Operation(summary = "Ajouter un logo à un jeu")
    @PutMapping("/{id}/logo")
    public String uploadLogo(@Parameter @PathVariable Long id,
                             @RequestParam("file") MultipartFile file) throws IOException {
        return gameService.addLogo(id, file);
    }
    @Operation(summary = "Ajouter une image ou une vidéo à un jeu")
    @PutMapping("/{id}/media")
    public String uploadFile(@Parameter @PathVariable Long id,
                             @Parameter @RequestParam MediaType mediaType,
                             @RequestParam("file") MultipartFile file) throws IOException {
        return gameService.uploadFile(id, file, mediaType);
    }
}
