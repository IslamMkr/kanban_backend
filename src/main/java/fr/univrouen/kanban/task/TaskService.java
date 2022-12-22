package fr.univrouen.kanban.task;

import fr.univrouen.kanban.list.ListRepository;
import fr.univrouen.kanban.user.User;
import fr.univrouen.kanban.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    // Task repository
    private final TaskRepository taskRepository;
    private final ListRepository listRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
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
    public Task saveTask(Task task) {
        return taskRepository.save(task);
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

    @Transactional
    public Task updateTaskList(Long tid, Long lid) {
        Optional<Task> optionalTask = taskRepository.findById(tid);
        Optional<fr.univrouen.kanban.list.List> optionalList = listRepository.findById(lid);

        if (!optionalTask.isPresent()) {
            return null;
        }

        if (!optionalList.isPresent()) {
            return null;
        }

        Task task = optionalTask.get();

        task.setList(optionalList.get());

        return task;
    }

    public List<Task> getUserTasks(Long uid) {
        List<Task> userTasks = taskRepository.getUserTasks(uid);

        for (Task task: userTasks) {
            task.getKanban().getOwner().setPassword(null);
            task.getUser().setPassword(null);

            List<User> members = task.getKanban().getMembers();

            for (User user : members) {
                user.setPassword(null);
            }
        }

        return userTasks;
    }
}