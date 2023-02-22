package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.constante.EntityType;
import fr.ul.mygameslibapirest.dto.filters.EditorFilter;
import fr.ul.mygameslibapirest.dto.requestbody.editor.CreateEditorBody;
import fr.ul.mygameslibapirest.dto.requestbody.editor.PatchEditorBody;
import fr.ul.mygameslibapirest.entity.Editor;
import fr.ul.mygameslibapirest.entity.Media;
import fr.ul.mygameslibapirest.repository.EditorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EditorService extends IService<Editor, Long, EditorRepository> {

    private final MediaService mediaService;

    protected EditorService(EditorRepository repository,
                            MediaService mediaService) {
        super(repository, "Editeur");
        this.mediaService = mediaService;
    }

    public Page<Editor> getAll(EditorFilter filter, Pageable pageable) {
        return repository.findAll(filter, pageable);
    }
    public Long getLogo(Long id) {
        return get(id).getLogo().getId();
    }

    public Editor create(CreateEditorBody body) {
        Editor entity;

        entity = Editor.builder()
                .name(body.getName())
                .build();

        return super.save(entity);
    }

    public Editor update(Long id, PatchEditorBody body) {
        Editor entity;

        entity = get(id);
        entity.setName(body.getName() == null ? entity.getName() : body.getName());

        return super.save(entity);
    }

    @Transactional
    public String addLogo(Long id, MultipartFile file) throws IOException {
        Editor entity;
        Media media;

        entity = get(id);
        media = mediaService.uploadLogo(id, EntityType.EDITOR, file);
        entity.setLogo(media);

        return mediaService.getPath(media);
    }
}
