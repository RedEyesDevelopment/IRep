package irepdata.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 13.11.2016.
 */
@Entity
@Table(name = "ideas")
public class Idea {
    private long id;
    private String name;
    private String description;
    private Set<Tag> tags = new HashSet<Tag>();
    private String content;
    private int rating;
    private User author;
    private Timestamp posted;
    private Timestamp viewed;
    private Long viewedCount;
    private boolean isEnabled;
    private Set<Comment> comments = new HashSet<Comment>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IDEA_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "IDEA_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_magazine", joinColumns = @JoinColumn(name = "TAG_IDEA_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_MAG_ID"))
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Column(name = "IDEA_CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "RATING")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDEA_AUTHOR_ID")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "POSTED_DATE")
    public Timestamp getPosted() {
        return posted;
    }

    public void setPosted(Timestamp posted) {
        this.posted = posted;
    }

    @Column(name = "VIEWED_DATE")
    public Timestamp getViewed() {
        return viewed;
    }

    public void setViewed(Timestamp viewed) {
        this.viewed = viewed;
    }

    @Column(name = "VIEWED_COUNT")
    public Long getViewedCount() {
        return viewedCount;
    }

    public void setViewedCount(Long viewedCount) {
        this.viewedCount = viewedCount;
    }

    @Column(name = "IDEA_ENABLED")
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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

        Idea idea = (Idea) o;

        if (getRating() != idea.getRating()) return false;
        if (isEnabled() != idea.isEnabled()) return false;
        if (!getName().equals(idea.getName())) return false;
        if (!getDescription().equals(idea.getDescription())) return false;
        if (!getContent().equals(idea.getContent())) return false;
        if (!getAuthor().equals(idea.getAuthor())) return false;
        if (!getPosted().equals(idea.getPosted())) return false;
        if (!getViewed().equals(idea.getViewed())) return false;
        return getViewedCount().equals(idea.getViewedCount());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getRating();
        result = 31 * result + getPosted().hashCode();
        result = 31 * result + getViewed().hashCode();
        result = 31 * result + getViewedCount().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        return result;
    }
}
