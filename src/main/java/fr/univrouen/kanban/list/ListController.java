package fr.univrouen.kanban.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/lists")
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping(path = "/")
    public java.util.List<fr.univrouen.kanban.list.List> getLists() {
        return listService.getLists();
    }

    @PostMapping(path = "save")
    public String saveList(List list) {
        return listService.saveList(list);
    }

    @GetMapping(path = "/{lid}")
    public List getListById(@PathVariable("lid") Long lid) {
        return listService.getListById(lid);
    }

    @GetMapping(path = "/kanban/{kid}")
    public java.util.List<fr.univrouen.kanban.list.List> getKanbanLists(@PathVariable("kid") Long kid) {
        return listService.getKanbanLists(kid);
    }

    @DeleteMapping(path = "/delete/{lid}")
    public String deleteList(@PathVariable("lid") Long lid) {
        return listService.deleteList(lid);
    }

}
