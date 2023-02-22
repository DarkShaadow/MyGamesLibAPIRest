package fr.ul.mygameslibapirest.repository;

import fr.ul.mygameslibapirest.dto.filters.EditorFilter;
import fr.ul.mygameslibapirest.entity.Editor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditorRepository extends JpaRepository<Editor, Long> {

    @Query("""
            SELECT editor
            FROM Editor AS editor
            WHERE (editor.name LIKE %:#{#filter.name}% OR :#{#filter.name} IS NULL)
""")
    Page<Editor> findAll(@Param("filter") EditorFilter filter, Pageable pageable);
}
