package fr.ul.mygameslibapirest.service;

import fr.ul.mygameslibapirest.entity.IEntity;
import fr.ul.mygameslibapirest.exception.ErrorMessageConstante;
import fr.ul.mygameslibapirest.exception.FunctionalException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class IService<Entity extends IEntity<Id>, Id, Repository extends JpaRepository<Entity, Id>> {

    protected final Repository repository;
    protected final String entityName;

    protected IService(Repository repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    public Entity get(Id id) {
        return repository.findById(id)
                .orElseThrow(() -> new FunctionalException(ErrorMessageConstante.MISSING_RESOURCE, entityName, String.valueOf(id)));
    }
    public boolean exists(Id id) {
        return repository.existsById(id);
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    protected Entity save(Entity entity) {
        return repository.save(entity);
    }

    public boolean delete(Id id) {
        repository.deleteById(id);

        return !exists(id);
    }
    public boolean delete(Entity entity) {
        return delete(entity.getId());
    }
}
