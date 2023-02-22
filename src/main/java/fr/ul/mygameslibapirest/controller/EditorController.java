package fr.ul.mygameslibapirest.controller;

import fr.ul.mygameslibapirest.dto.EditorDto;
import fr.ul.mygameslibapirest.dto.filters.EditorFilter;
import fr.ul.mygameslibapirest.dto.requestbody.editor.CreateEditorBody;
import fr.ul.mygameslibapirest.dto.requestbody.editor.PatchEditorBody;
import fr.ul.mygameslibapirest.mapper.EditorMapper;
import fr.ul.mygameslibapirest.service.EditorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(maxAge = 3600)
@Tag(name = "Editor")
@RestController
@RequestMapping("/editor")
public class EditorController {

    private final EditorMapper editorMapper;
    private final EditorService editorService;

    public EditorController(EditorMapper editorMapper,
                            EditorService editorService) {
        this.editorMapper = editorMapper;
        this.editorService = editorService;
    }

    @Operation(summary = "Retourne les editeurs paginés")
    @GetMapping("")
    public Page<EditorDto> getAll(@Parameter @RequestParam(required = false) Integer size,
                                  @Parameter @RequestParam(required = false) Integer page,
                                  @Parameter @RequestParam(required = false) String name) {
        return editorService.getAll(
                        EditorFilter.builder()
                                .name(name)
                                .build(),
                        PageRequest.of((page == null ? 0 : page), (size == null ? Integer.MAX_VALUE : size)))
                .map(editorMapper::to);
    }

    @Operation(summary = "Retourne un éditeur")
    @GetMapping("/{id}")
    public EditorDto get(@Parameter @PathVariable Long id) {
        return editorMapper.to(editorService.get(id));
    }
    @Operation(summary = "Retourne le logo d'un éditeur")
    @GetMapping("/{id}/logo")
    public Long getMedias(@Parameter @PathVariable Long id) {
        return editorService.getLogo(id);
    }

    @Operation(summary = "Créer un éditeur")
    @PostMapping("")
    public EditorDto create(@RequestBody CreateEditorBody body) {
        return editorMapper.to(editorService.create(body));
    }
    @Operation(summary = "Mettre à jour un éditeur")
    @PatchMapping("/{id}")
    public EditorDto update(@Parameter @PathVariable Long id,
                            @RequestBody PatchEditorBody body) {
        return editorMapper.to(editorService.update(id, body));
    }
    @Operation(summary = "Supprimer un éditeur")
    @DeleteMapping("/{id}")
    public boolean delete(@Parameter @PathVariable Long id) {
        return editorService.delete(id);
    }

    @Operation(summary = "Ajouter un logo à un éditeur")
    @PutMapping("/{id}/logo")
    public String uploadLogo(@Parameter @PathVariable Long id,
                             @RequestParam("file") MultipartFile file) throws IOException {
        return editorService.addLogo(id, file);
    }
}
