package irepdata.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 27.11.2016.
 */
@Entity
@Table(name = "contents")
public class Content {
    private long id;
    private Idea idea;
    private String contentData;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CONTENT_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "ideas")
    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    @Column(name = "IDEA_CONTENT")
    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (getId() != content.getId()) return false;
        if (!getIdea().equals(content.getIdea())) return false;
        return getContentData() != null ? getContentData().equals(content.getContentData()) : content.getContentData() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getIdea().hashCode();
        result = 31 * result + (getContentData() != null ? getContentData().hashCode() : 0);
        return result;
    }
}
