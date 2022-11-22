package fr.univrouen.kanban.task;

import fr.univrouen.kanban.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    // Task repository
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Returns all tasks in DB.
     * @return
     */
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * Returns a task with a specific id
     * or a null if id doesn't exists.
     * @param tid
     * @return
     */
    public Task getTaskByTid(Long tid) {
        Optional<Task> optionalTask = taskRepository.findById(tid);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }

        return null;
    }

    /**
     * Saves a task
     * Returns a success message
     * @param task
     * @return
     */
    public String saveTask(Task task) {
        taskRepository.save(task);

        return Consts.TASK_SAVED;
    }

    /**
     * Deletes a task with a specific id
     * If task doesn't exist, it returns an error message
     * @param tid
     * @return
     */
    public String deleteTask(Long tid) {
        boolean taskExists = taskRepository.existsById(tid);

        if (taskExists) {
            taskRepository.deleteById(tid);

            return Consts.TASK_DELETED;
        }

        return Consts.TASK_NOT_FOUND;
    }

    /**
     * Returns all tasks belonging to a list
     * @param lid
     * @return
     */
    public List<Task> getListTasks(Long lid) {
        return taskRepository.getListTasks(lid);
    }

    /**
     * Returns all tasks belonging to a kanban
     * @param kid
     * @return
     */
    public List<Task> getKanbanTasks(Long kid) {
        return taskRepository.getKanbanTasks(kid);
    }

}