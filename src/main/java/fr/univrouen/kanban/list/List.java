package fr.univrouen.kanban.list;

import com.sun.istack.NotNull;
import fr.univrouen.kanban.kanban.Kanban;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long lid;
    @NonNull
    @NotNull
    private String title;

    @NonNull
    @NotNull
    @ManyToOne
    private Kanban kanban;

    public List() {}

    public List(Long lid, @NonNull String title, @NonNull Kanban kanban) {
        this.lid = lid;
        this.title = title;
        this.kanban = kanban;
    }

    public List(@NonNull String title, @NonNull Kanban kanban) {
        this.title = title;
        this.kanban = kanban;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(@NonNull Kanban kanban) {
        this.kanban = kanban;
    }

    @Override
    public String toString() {
        return "List{" +
                "lid=" + lid +
                ", title='" + title + '\'' +
                ", kanban=" + kanban +
                '}';
    }
}
