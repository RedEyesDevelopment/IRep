package irepdata.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Gvozd on 27.11.2016.
 */
@Entity
@Table(name = "contents")
public class Content implements Serializable {
    private Idea idea;
    private String contentData;

    public Content() {
    }

    public Content(Idea idea, String contentData) {
        this.idea = idea;
        this.contentData = contentData;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CONTENT_ID")
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

        if (!getIdea().equals(content.getIdea())) return false;
        return getContentData() != null ? getContentData().equals(content.getContentData()) : content.getContentData() == null;

    }

    @Override
    public int hashCode() {
        int result = getIdea().hashCode();
        result = 31 * result + (getContentData() != null ? getContentData().hashCode() : 0);
        return result;
    }
}
