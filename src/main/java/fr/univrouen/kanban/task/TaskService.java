package fr.univrouen.kanban.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskByTid(Long tid) {
        Optional<Task> optionalTask = taskRepository.findById(tid);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }

        return null;
    }
}