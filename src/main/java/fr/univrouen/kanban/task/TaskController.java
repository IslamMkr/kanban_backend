package fr.univrouen.kanban.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tasks")
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

    @PostMapping(path = "save")
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @DeleteMapping(path = "delete/{tid}")
    public String deleteTask(@PathVariable("tid") Long tid) {
        return taskService.deleteTask(tid);
    }

    @GetMapping(path = "/list/{lid}")
    public List<Task> getListTasks(@PathVariable("lid") Long lid) {
        return taskService.getListTasks(lid);
    }

    @GetMapping(path = "/kanban/{kid}")
    public List<Task> getKanbanTasks(@PathVariable("kid") Long kid) {
        return taskService.getKanbanTasks(kid);
    }

    @PutMapping(path = "/{tid}/list/{lid}")
    public Task updateTaskList(@PathVariable("tid") Long tid, @PathVariable("lid") Long lid) {
        return taskService.updateTaskList(tid, lid);
    }

    @GetMapping(path = "/user/{uid}")
    public List<Task> getUserTasks(@PathVariable("uid") Long uid) {
        return taskService.getUserTasks(uid);
    }

}
