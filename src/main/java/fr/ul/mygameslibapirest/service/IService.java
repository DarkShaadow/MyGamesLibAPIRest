package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.entity.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class IService<Entity extends IEntity<Key>, Key, Repository extends JpaRepository<Entity, Key>> {

    protected final Repository repository;

    protected IService(Repository repository) {
        this.repository = repository;
    }

    public Entity get(Key id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucune entité trouvée pour l'id " + id));
    }
    public boolean exists(Key id) {
        return repository.existsById(id);
    }
}
