package fr.univrouen.kanban.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "/{tid}")
    public Task getTaskByTid(@PathVariable("tid") Long tid) {
        return taskService.getTaskByTid(tid);
    }

}
