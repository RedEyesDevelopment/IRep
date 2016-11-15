package irepdata.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 26.03.2016.
 */
@Entity
@NamedQueries({@NamedQuery(name = "User.findById", query="select distinct c from User c where c.id=:id")})
@Table(name = "users")
public class User implements Serializable {
    private Long id;
    private String username;
    private String login;
    private String password;
    private boolean admin;
    private boolean enabled;
    private Set<Idea> ideas = new HashSet<Idea>();
    private Set<Comment> comments = new HashSet<Comment>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USER_NAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PSWD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_TYPE")
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Column(name = "USER_ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(Set<Idea> ideas) {
        this.ideas = ideas;
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isAdmin() != user.isAdmin()) return false;
        if (isEnabled() != user.isEnabled()) return false;
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        return getPassword().equals(user.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (isAdmin() ? 1 : 0);
        result = 31 * result + (isEnabled() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", enabled=" + enabled +'}';
    }


}
