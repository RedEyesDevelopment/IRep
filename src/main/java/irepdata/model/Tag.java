package irepdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import irepdata.views.JSONViews;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 13.11.2016.
 */
@Entity
@Table(name = "TAGS")
public class Tag implements Serializable{
    @JsonView(JSONViews.List.class)
    private Long id;
    @JsonView(JSONViews.List.class)
    private String content;
    @JsonView(JSONViews.Admin.class)
    private Timestamp created;
    @JsonView(JSONViews.Admin.class)
    private boolean enabled;
    @JsonIgnore
    private Set<Idea> ideas = new HashSet<Idea>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TAG_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TAG_CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "TAG_CREATED")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TAG_MAGAZINE", joinColumns = @JoinColumn(name = "TAG_MAG_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_IDEA_ID"))
    public Set<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(Set<Idea> ideas) {
        this.ideas = ideas;
    }

    @Column(name = "TAG_ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (!id.equals(tag.id)) return false;
        if (content != null ? !content.equals(tag.content) : tag.content != null) return false;
        return created != null ? created.equals(tag.created) : tag.created == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", enabled=" + enabled +
                '}';
    }
}
