package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.dto.requestbody.editor.CreateEditorBody;
import fr.ul.mygameslibapirest.dto.requestbody.editor.PatchEditorBody;
import fr.ul.mygameslibapirest.entity.Editor;
import fr.ul.mygameslibapirest.repository.EditorRepository;
import org.springframework.stereotype.Service;

@Service
public class EditorService extends IService<Editor, Long, EditorRepository> {

    protected EditorService(EditorRepository repository) {
        super(repository, "Editeur");
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
}
