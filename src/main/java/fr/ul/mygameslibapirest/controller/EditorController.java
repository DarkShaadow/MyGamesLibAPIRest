package fr.ul.mygameslibapirest.controller;

import fr.ul.mygameslibapirest.dto.EditorDto;
import fr.ul.mygameslibapirest.dto.requestbody.editor.CreateEditorBody;
import fr.ul.mygameslibapirest.dto.requestbody.editor.PatchEditorBody;
import fr.ul.mygameslibapirest.mapper.EditorMapper;
import fr.ul.mygameslibapirest.service.EditorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<EditorDto> getAll() {
        return editorService.getAll()
                .stream()
                .map(editorMapper::to)
                .collect(Collectors.toList());
    }
    @Operation(summary = "Retourne un éditeur")
    @GetMapping("/{id}")
    public EditorDto get(@Parameter @PathVariable Long id) {
        return editorMapper.to(editorService.get(id));
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
}
