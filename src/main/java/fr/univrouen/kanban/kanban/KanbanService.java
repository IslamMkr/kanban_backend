package fr.univrouen.kanban.kanban;

import fr.univrouen.kanban.utils.Consts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KanbanService {

    private final KanbanRepository kanbanRepository;

    public KanbanService(KanbanRepository kanbanRepository) {
        this.kanbanRepository = kanbanRepository;
    }

    public List<Kanban> getKanbans() {
        return kanbanRepository.findAll();
    }

    public Kanban getKanbanById(Long kid) {
        Optional<Kanban> optionalKanban = kanbanRepository.findById(kid);

        if (optionalKanban.isPresent()) {
            return optionalKanban.get();
        }

        return null;
    }

    public String saveKanban(Kanban kanban) {
        kanbanRepository.save(kanban);

        return Consts.KANBAN_SAVED;
    }

    public List<Kanban> getUserKanbans(Long uid) {
        return kanbanRepository.getUserKanbans(uid);
    }

    public List<Kanban> getPublicKanbans() {
        return kanbanRepository.getPublicKanbans(Consts.KANBAN_VISIBILITY_PUBLIC);
    }

    public String deleteKanban(Long kid) {
        boolean kanbanExists = kanbanRepository.existsById(kid);

        if (kanbanExists) {
            kanbanRepository.deleteById(kid);

            return Consts.KANBAN_DELETED;
        }

        return Consts.KANBAN_NOT_FOUND;
    }

}
