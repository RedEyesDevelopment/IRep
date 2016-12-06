package irepdata.model;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.views.JSONViews;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 13.11.2016.
 */
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @JsonView(JSONViews.List.class)
    private long id;
    @JsonView(JSONViews.List.class)
    private User author;
    private Idea idea;
    @JsonView(JSONViews.List.class)
    private String content;
    @JsonView(JSONViews.List.class)
    private Timestamp posted;
    @JsonView(JSONViews.Admin.class)
    private boolean enabled;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "COMMENT_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMMENT_AUTHOR_ID")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMMENT_IDEA_ID")
    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    @Column(name = "COMMENT_CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "COMMENT_POSTED")
    public Timestamp getPosted() {
        return posted;
    }

    public void setPosted(Timestamp posted) {
        this.posted = posted;
    }

    @Column(name = "COMMENT_ENABLED")
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

        Comment comment = (Comment) o;

        if (getId() != comment.getId()) return false;
        if (isEnabled() != comment.isEnabled()) return false;
        if (!getAuthor().equals(comment.getAuthor())) return false;
        if (!getIdea().equals(comment.getIdea())) return false;
        if (getContent() != null ? !getContent().equals(comment.getContent()) : comment.getContent() != null)
            return false;
        return getPosted() != null ? getPosted().equals(comment.getPosted()) : comment.getPosted() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getIdea().hashCode();
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getPosted() != null ? getPosted().hashCode() : 0);
        result = 31 * result + (isEnabled() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", idea=" + idea +
                ", content='" + content + '\'' +
                ", posted=" + posted +
                ", enabled=" + enabled +
                '}';
    }
}
