package fr.univrouen.kanban.task;

import fr.univrouen.kanban.kanban.Kanban;
import fr.univrouen.kanban.list.List;
import fr.univrouen.kanban.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long tid;
    private String description;
    private LocalDate time_limit;

    @ManyToOne
    private Kanban kanban;

    @ManyToOne
    private User user;

    @ManyToOne
    private List list;

    public Task() {}

    public Task(
                Long tid,
                String description,
                LocalDate time_limit,
                Kanban kanban,
                User user,
                List list
    ) {
        this.tid = tid;
        this.description = description;
        this.time_limit = time_limit;
        this.kanban = kanban;
        this.user = user;
        this.list = list;
    }

    public Task(
                String description,
                LocalDate time_limit,
                Kanban kanban,
                User user,
                List list
    ) {
        this.description = description;
        this.time_limit = time_limit;
        this.kanban = kanban;
        this.user = user;
        this.list = list;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(LocalDate time_limit) {
        this.time_limit = time_limit;
    }

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tid=" + tid +
                ", description='" + description + '\'' +
                ", time_limit=" + time_limit +
                ", kanban=" + kanban +
                ", user=" + user +
                ", list=" + list +
                '}';
    }
}
