package fr.univrouen.kanban.task;

import com.sun.istack.NotNull;
import fr.univrouen.kanban.kanban.Kanban;
import fr.univrouen.kanban.list.List;
import fr.univrouen.kanban.user.User;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long tid;
    @NonNull
    @NotNull
    private String title;
    @NonNull
    @NotNull
    private String description;

    private LocalDateTime time_limit;

    @NonNull
    @NotNull
    @ManyToOne
    private Kanban kanban;

    @ManyToOne
    private User user;

    @NonNull
    @NotNull
    @ManyToOne
    private List list;

    public Task() {}

    public Task(Long tid, @NonNull String title, @NonNull String description, LocalDateTime time_limit, @NonNull Kanban kanban, User user, @NonNull List list) {
        this.tid = tid;
        this.title = title;
        this.description = description;
        this.time_limit = time_limit;
        this.kanban = kanban;
        this.user = user;
        this.list = list;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public Task(@NonNull String title, @NonNull String description, LocalDateTime time_limit, @NonNull Kanban kanban, User user, @NonNull List list) {
        this.title = title;
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

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public LocalDateTime getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(LocalDateTime time_limit) {
        this.time_limit = time_limit;
    }

    @NonNull
    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(@NonNull Kanban kanban) {
        this.kanban = kanban;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NonNull
    public List getList() {
        return list;
    }

    public void setList(@NonNull List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tid=" + tid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time_limit=" + time_limit +
                ", kanban=" + kanban +
                ", user=" + user +
                ", list=" + list +
                '}';
    }
}
