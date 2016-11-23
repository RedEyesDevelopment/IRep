package irepdata.model;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 13.11.2016.
 */
@Entity
@Table(name = "comments")
public class Comment {
    private long id;
    private User author;
    private Idea idea;
    private String content;
    private Timestamp posted;
    private boolean isEnabled;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "COMMENT_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_AUTHOR_ID")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (isEnabled() != comment.isEnabled()) return false;
        if (!getIdea().equals(comment.getIdea())) return false;
        if (!getContent().equals(comment.getContent())) return false;
        return getPosted().equals(comment.getPosted());

    }

    @Override
    public int hashCode() {
        int result = getIdea().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getPosted().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", posted=" + posted +
                ", isEnabled=" + isEnabled +
                ", idea=" + idea +
                '}';
    }
}
