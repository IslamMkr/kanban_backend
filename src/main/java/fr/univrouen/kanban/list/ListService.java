package fr.univrouen.kanban.list;

import fr.univrouen.kanban.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public java.util.List<fr.univrouen.kanban.list.List> getLists() {
        return listRepository.findAll();
    }

    public String saveList(List list) {
        listRepository.save(list);

        return Consts.LIST_SAVED;
    }

    public List getListById(Long lid) {
        Optional<List> optionalList = listRepository.findById(lid);

        if (optionalList.isPresent()) {
            return optionalList.get();
        }

        return null;
    }

    public java.util.List<fr.univrouen.kanban.list.List> getKanbanLists(Long kid) {
        return listRepository.getKanbanLists(kid);
    }

    public String deleteList(Long lid) {
        boolean listExists = listRepository.existsById(lid);

        if (listExists) {
            listRepository.deleteById(lid);

            return Consts.LIST_DELETED;
        }

        return Consts.LIST_NOT_FOUND;
    }

}
