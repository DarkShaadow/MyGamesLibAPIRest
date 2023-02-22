package fr.ul.mygameslibapirest.repository;

import fr.ul.mygameslibapirest.dto.filters.GameFilter;
import fr.ul.mygameslibapirest.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("""
            SELECT game
            FROM Game AS game
            WHERE (game.name LIKE %:#{#filter.name}% OR :#{#filter.name} IS NULL)
              AND (game.editor.id = :#{#filter.editorId} OR :#{#filter.editorId} IS NULL)
""")
    Page<Game> findAll(@Param("filter") GameFilter filter, Pageable pageable);
}
