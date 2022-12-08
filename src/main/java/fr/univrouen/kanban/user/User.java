package fr.univrouen.kanban.user;

import fr.univrouen.kanban.kanban.Kanban;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long uid;
    private String firstname;
    private String lastname;
    @Column(name = "username", length = 50, unique = true)
    private String username;
    private String email;
    private String password;
    @OneToMany
    private List<Kanban> kanbans;

    public User() {}

    public User(Long uid, String firstname, String lastname, String username, String email, String password, List<Kanban> kanbans) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.kanbans = kanbans;
    }

    public User(String firstname, String lastname, String username, String email, String password, List<Kanban> kanbans) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.kanbans = kanbans;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Kanban> getKanbans() {
        return kanbans;
    }

    public void setKanbans(List<Kanban> kanbans) {
        this.kanbans = kanbans;
    }

}

