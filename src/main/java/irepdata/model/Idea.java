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
@NamedQueries({@NamedQuery(name = "Idea.findIdeaWithTags", query="select distinct i from Idea i left join fetch i.tags t")})
@Table(name = "ideas")
public class Idea {
    private long id;
    private String name;
    private String description;
    private String image;
    private Set<Tag> tags = new HashSet<Tag>();
    private Content content;
    private int liked;
    private int disliked;
    private User author;
    private Timestamp posted;
    private Timestamp viewed;
    private Long viewedCount;
    private boolean enabled;
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

    @Column(name = "IDEA_IMAGE")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tag_magazine", joinColumns = @JoinColumn(name = "TAG_IDEA_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_MAG_ID"))
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @OneToOne(mappedBy = "idea", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Column(name = "IDEA_LIKES")
    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public void like(){
        liked++;
    }

    @Column(name = "IDEA_DISLIKES")
    public int getDisliked() {
        return disliked;
    }

    public void setDisliked(int disliked) {
        this.disliked = disliked;
    }

    public void dislike(){
        disliked++;
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
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

        if (isEnabled() != idea.isEnabled()) return false;
        if (!getName().equals(idea.getName())) return false;
        if (!getDescription().equals(idea.getDescription())) return false;
        if (!getAuthor().equals(idea.getAuthor())) return false;
        if (!getPosted().equals(idea.getPosted())) return false;
        if (!getViewed().equals(idea.getViewed())) return false;
        return getViewedCount().equals(idea.getViewedCount());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPosted().hashCode();
        result = 31 * result + getViewed().hashCode();
        result = 31 * result + getViewedCount().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        return result;
    }

    public String toStringWithAll() {
        return "Idea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", tags=" + tags +
                ", content=" + content +
                ", liked=" + liked +
                ", disliked=" + disliked +
                ", author=" + author +
                ", posted=" + posted +
                ", viewed=" + viewed +
                ", viewedCount=" + viewedCount +
                ", enabled=" + enabled +
                ", comments=" + comments +
                '}';
    }

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", liked=" + liked +
                ", disliked=" + disliked +
                ", author=" + author +
                ", posted=" + posted +
                ", viewed=" + viewed +
                ", viewedCount=" + viewedCount +
                ", enabled=" + enabled +
                '}';
    }
}
