package fr.univrouen.kanban.kanban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KanbanRepository extends JpaRepository<Kanban, Long> {

    @Query("SELECT k FROM Kanban k WHERE k.owner = ?1")
    List<Kanban> getUserKanbans(Long uid);

    @Query("SELECT k FROM Kanban k WHERE k.visibility = ?1")
    List<Kanban> getPublicKanbans(String visibility);

}
