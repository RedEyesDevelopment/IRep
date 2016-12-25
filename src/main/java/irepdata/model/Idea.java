package irepdata.model;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.views.JSONViews;

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
@Table(name = "IDEAS")
public class Idea {

    @Transient
    public static final int MAXIDEASSHOWINGCAPACITY = 10;

    @JsonView(JSONViews.List.class)
    private long id;
    @JsonView(JSONViews.List.class)
    private String name;
    @JsonView(JSONViews.List.class)
    private String description;
    @JsonView(JSONViews.List.class)
    private String image;
    @JsonView(JSONViews.List.class)
    private Set<Tag> tags = new HashSet<Tag>();
    @JsonView(JSONViews.Data.class)
    private Content content;
    @JsonView(JSONViews.List.class)
    private int liked;
    @JsonView(JSONViews.List.class)
    private int disliked;
    @JsonView(JSONViews.List.class)
    private User author;
    @JsonView(JSONViews.List.class)
    private Timestamp posted;
    @JsonView(JSONViews.List.class)
    private Timestamp viewed;
    @JsonView(JSONViews.Admin.class)
    private Long viewedCount;
    @JsonView(JSONViews.Admin.class)
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
    @JoinTable(name = "TAG_MAGAZINE", joinColumns = @JoinColumn(name = "TAG_IDEA_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_MAG_ID"))
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="IDEA_CONTENT_ID", unique=true, nullable=false)
//    @LazyToOne(value = LazyToOneOption.NO_PROXY)
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Transient
    public Long getContentId(){
        return content.getId();
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

    @ManyToOne(fetch = FetchType.EAGER)
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

    public void watch(){
        this.posted = new Timestamp(System.currentTimeMillis());
    }

    @Column(name = "VIEWED_COUNT")
    public Long getViewedCount() {
        return viewedCount;
    }

    public void setViewedCount(Long viewedCount) {
        this.viewedCount = viewedCount;
    }

    public void addViewedCount(){
        viewedCount++;
    }

    @Column(name = "IDEA_ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(mappedBy = "idea", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("posted ASC")
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

        if (getLiked() != idea.getLiked()) return false;
        if (getDisliked() != idea.getDisliked()) return false;
        if (isEnabled() != idea.isEnabled()) return false;
        if (getName() != null ? !getName().equals(idea.getName()) : idea.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(idea.getDescription()) : idea.getDescription() != null)
            return false;
        if (getImage() != null ? !getImage().equals(idea.getImage()) : idea.getImage() != null) return false;
        if (!getContent().equals(idea.getContent())) return false;
        if (!getAuthor().equals(idea.getAuthor())) return false;
        if (!getPosted().equals(idea.getPosted())) return false;
        if (!getViewed().equals(idea.getViewed())) return false;
        return getViewedCount() != null ? getViewedCount().equals(idea.getViewedCount()) : idea.getViewedCount() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + getLiked();
        result = 31 * result + getDisliked();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getPosted().hashCode();
        result = 31 * result + getViewed().hashCode();
        result = 31 * result + (getViewedCount() != null ? getViewedCount().hashCode() : 0);
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
