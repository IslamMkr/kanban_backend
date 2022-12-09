package fr.univrouen.kanban.kanban;

import fr.univrouen.kanban.user.User;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "kanbans")
public class Kanban {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long kid;
    @NonNull
    private String title;
    @NonNull
    private String visibility;

    @NonNull
    @ManyToOne
    private User user;

    public Kanban() {}

    public Kanban(Long kid, String title, String visibility, User user) {
        this.kid = kid;
        this.title = title;
        this.visibility = visibility;
        this.user = user;
    }

    public Kanban(String title, String visibility, User user) {
        this.title = title;
        this.visibility = visibility;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Kanban{" +
                "kid=" + kid +
                ", title='" + title + '\'' +
                ", visibility='" + visibility + '\'' +
                ", user=" + user +
                '}';
    }
}
