package fr.univrouen.kanban.kanban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/kanbans")
public class KanbanController {

    private final KanbanService kanbanService;

    @Autowired
    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @GetMapping(path = "/")
    public List<Kanban> getKanbans() {
        return kanbanService.getKanbans();
    }

    @GetMapping(path = "/{kid}")
    public Kanban getKanbanById(@PathVariable("kid") Long kid) {
        return kanbanService.getKanbanById(kid);
    }

    @PostMapping(path = "save")
    public Kanban saveKanban(@RequestBody Kanban kanban) {
        return kanbanService.saveKanban(kanban);
    }

    @GetMapping(path = "/user/{uid}")
    public List<Kanban> getUserKanbans(@PathVariable("uid") Long uid) {
        return kanbanService.getUserKanbans(uid);
    }

    @GetMapping(path = "public")
    public List<Kanban> getPublicKanbans() {
        return kanbanService.getPublicKanbans();
    }

    @DeleteMapping(path = "/delete/{kid}")
    public String deleteKanban(@PathVariable("kid") Long kid) {
        return kanbanService.deleteKanban(kid);
    }

}
