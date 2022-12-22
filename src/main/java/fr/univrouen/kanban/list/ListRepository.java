package fr.univrouen.kanban.list;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

    @Query("SELECT l FROM List l WHERE l.kanban.kid = ?1")
    java.util.List<fr.univrouen.kanban.list.List> getKanbanLists(Long kid);

}
