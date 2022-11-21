package fr.univrouen.kanban.list;

import fr.univrouen.kanban.kanban.Kanban;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long lid;
    private String title;

    @ManyToOne
    private Kanban kanban;

    public List() {}

    public List(Long lid, String title, Kanban kanban) {
        this.lid = lid;
        this.title = title;
        this.kanban = kanban;
    }

    public List(String title, Kanban kanban) {
        this.title = title;
        this.kanban = kanban;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
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
