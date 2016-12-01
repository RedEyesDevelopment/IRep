package irepdata.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Gvozd on 27.11.2016.
 */
@Entity
@Table(name = "contents")
public class Content implements Serializable {
    private Long id;
    private Idea idea;
    private String contentData;


    public Content() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CONTENT_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne()
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

        if (!getId().equals(content.getId())) return false;
        return getContentData() != null ? getContentData().equals(content.getContentData()) : content.getContentData() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getContentData() != null ? getContentData().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", contentData='" + contentData + '\'' +
                '}';
    }
}
