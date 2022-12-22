package fr.univrouen.kanban.kanban;

import fr.univrouen.kanban.list.ListRepository;
import fr.univrouen.kanban.user.User;
import fr.univrouen.kanban.user.UserRepository;
import fr.univrouen.kanban.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KanbanService {

    private final KanbanRepository kanbanRepository;
    private final ListRepository listRepository;
    private final UserRepository userRepository;

    @Autowired
    public KanbanService(KanbanRepository kanbanRepository, ListRepository listRepository, UserRepository userRepository) {
        this.kanbanRepository = kanbanRepository;
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }

    public List<Kanban> getKanbans() {
        List<Kanban> kanbans = kanbanRepository.findAll();

        for (Kanban kanban : kanbans) {
            kanban.getOwner().setPassword(null);

            int membersNumber = kanban.getMembers().size();

            for (int i = 0; i < membersNumber; ++i) {
                kanban.getMembers().get(i).setPassword(null);
            }
        }

        return kanbans;
    }

    public Kanban getKanbanById(Long kid) {
        Optional<Kanban> optionalKanban = kanbanRepository.findById(kid);

        if (optionalKanban.isPresent()) {
            Kanban kanban = optionalKanban.get();
            kanban.getOwner().setPassword(null);

            return optionalKanban.get();
        }

        return null;
    }

    public Kanban saveKanban(Kanban kanban) {
        kanban.getOwner().setPassword(null);

        Kanban savedKanban = kanbanRepository.save(kanban);

        List<fr.univrouen.kanban.list.List> kanbanLists = new ArrayList<>();
        kanbanLists.add(new fr.univrouen.kanban.list.List("Stories", savedKanban));
        kanbanLists.add(new fr.univrouen.kanban.list.List("Terminées", savedKanban));

        listRepository.saveAll(kanbanLists);

        return savedKanban;
    }

    public List<Kanban> getUserKanbans(Long uid) {
        List<Kanban> userInvitedKanbans = getKanbansWhereUserIsInvited(uid);

        List<Kanban> kanbans = kanbanRepository.getUserKanbans(uid);
        kanbans.addAll(userInvitedKanbans);

        for (Kanban kanban : kanbans) {
            kanban.getOwner().setPassword(null);

            int membersNumber = kanban.getMembers().size();

            for (int i = 0; i < membersNumber; ++i) {
                kanban.getMembers().get(i).setPassword(null);
            }
        }

        return kanbans;
    }

    public List<Kanban> getPublicKanbans() {
        List<Kanban> kanbans = kanbanRepository.getPublicKanbans(Consts.KANBAN_VISIBILITY_PUBLIC);

        for (Kanban kanban : kanbans) {
            kanban.getOwner().setPassword(null);
        }

        return kanbans;
    }

    public String deleteKanban(Long kid) {
        boolean kanbanExists = kanbanRepository.existsById(kid);

        if (kanbanExists) {
            kanbanRepository.deleteById(kid);

            return Consts.KANBAN_DELETED;
        }

        return Consts.KANBAN_NOT_FOUND;
    }

    @Transactional
    public Kanban addMemberToKanban(Long kid, String username) {
        Kanban kanban = kanbanRepository.findById(kid).get();

        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (!optionalUser.isPresent()) {
            return null;
        }

        List<User> members = kanban.getMembers();

        for (User user : members) {
            if (user.getUsername().equals(username)) {
                return null;
            }
        }

        kanban.getMembers().add(optionalUser.get());

        return kanban;
    }

    public List<User> getKanbanMembers(Long kid) {
        Kanban kanban = kanbanRepository.findById(kid).get();

        List<User> members = kanban.getMembers();

        for (User member : members) {
            member.setPassword(null);
        }

        return members;
    }

    public List<Kanban> getKanbansWhereUserIsInvited(Long uid) {
        List<Kanban> allKanbans = getKanbans();
        List<Kanban> kanbans = new ArrayList<>();

        for (Kanban kanban : allKanbans) {
            List<User> users = kanban.getMembers();

            boolean isInvited = false;

            for (User user : users) {
                if (Objects.equals(user.getUid(), uid)) {
                    isInvited = true;
                    break;
                }
            }

            if (isInvited) {
                kanbans.add(kanban);
            }
        }

        return kanbans;
    }

}
