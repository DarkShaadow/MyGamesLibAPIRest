package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.entity.Editor;
import fr.ul.mygameslibapirest.repository.EditorRepository;

public class EditorService extends IService<Editor, Long, EditorRepository> {

    protected EditorService(EditorRepository repository) {
        super(repository);
    }
}
