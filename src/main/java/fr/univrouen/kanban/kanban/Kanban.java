package fr.univrouen.kanban.kanban;

import fr.univrouen.kanban.user.User;

import javax.persistence.*;

@Entity
@Table(name = "kanbans")
public class Kanban {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long kid;
    private String title;
    private String visibility;

    @ManyToOne
    private User owner;

    public Kanban() {}

    public Kanban(Long kid, String title, String visibility, User owner) {
        this.kid = kid;
        this.title = title;
        this.visibility = visibility;
        this.owner = owner;
    }

    public Kanban(String title, String visibility, User owner) {
        this.title = title;
        this.visibility = visibility;
        this.owner = owner;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Kanban{" +
                "kid=" + kid +
                ", title='" + title + '\'' +
                ", visibility='" + visibility + '\'' +
                ", owner=" + owner +
                '}';
    }
}
