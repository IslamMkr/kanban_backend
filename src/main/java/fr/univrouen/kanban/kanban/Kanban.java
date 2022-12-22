package fr.univrouen.kanban.kanban;

import com.sun.istack.NotNull;
import fr.univrouen.kanban.user.User;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kanbans")
public class Kanban {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long kid;
    @NotNull
    @NonNull
    private String title;
    @NotNull
    @NonNull
    private String description;
    @NotNull
    @NonNull
    private String visibility;

    @NotNull
    @NonNull
    @ManyToOne
    private User owner;

    @OneToMany
    private List<User> members;

    public Kanban() {}

    public Kanban(Long kid, @NonNull String title, @NonNull String description, @NonNull String visibility, @NonNull User owner, List<User> members) {
        this.kid = kid;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
        this.owner = owner;
        this.members = members;
    }

    public Kanban(@NonNull String title, @NonNull String description, @NonNull String visibility, @NonNull User owner, List<User> members) {
        this.title = title;
        this.description = description;
        this.visibility = visibility;
        this.owner = owner;
        this.members = members;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(@NonNull String visibility) {
        this.visibility = visibility;
    }

    @NonNull
    public User getOwner() {
        return owner;
    }

    public void setOwner(@NonNull User owner) {
        this.owner = owner;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Kanban{" +
                "kid=" + kid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", visibility='" + visibility + '\'' +
                ", owner=" + owner +
                ", members=" + members +
                '}';
    }
}
