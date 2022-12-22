package fr.univrouen.kanban.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.list.lid = ?1")
    List<Task> getListTasks(Long lid);

    @Query("SELECT t FROM Task t WHERE t.kanban.kid = ?1")
    List<Task> getKanbanTasks(Long kid);

    @Query("SELECT t FROM Task t WHERE t.user.uid = ?1")
    List<Task> getUserTasks(Long uid);

}
