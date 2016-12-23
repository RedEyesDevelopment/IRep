package irepdata.model;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.views.JSONViews;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Gvozd on 13.12.2016.
 */
@Entity
@Table(name = "IMAGES")
public class Image {

    @Transient
    public static final int MAXIMAGESSHOWINGCAPACITY = 5;

    @JsonView(JSONViews.List.class)
    private Long id;
    @JsonView(JSONViews.List.class)
    private String imageName;
    @JsonView(JSONViews.List.class)
    private Timestamp posted;
    @JsonView(JSONViews.List.class)
    private Long imageAuthorId;
    @JsonView(JSONViews.List.class)
    private boolean publicity;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IMAGE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "IMAGE_NAME")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Column(name = "IMAGE_CREATED")
    public Timestamp getPosted() {
        return posted;
    }

    public void setPosted(Timestamp posted) {
        this.posted = posted;
    }

    @Column(name = "IMAGE_AUTHOR")
    public Long getImageAuthorId() {
        return imageAuthorId;
    }

    public void setImageAuthorId(Long imageAuthorId) {
        this.imageAuthorId = imageAuthorId;
    }

    @Column(name = "IMAGE_PUBLIC")
    public boolean isPublicity() {
        return publicity;
    }

    public void setPublicity(boolean publicity) {
        this.publicity = publicity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (isPublicity() != image.isPublicity()) return false;
        if (getImageName() != null ? !getImageName().equals(image.getImageName()) : image.getImageName() != null)
            return false;
        return getPosted().equals(image.getPosted());

    }

    @Override
    public int hashCode() {
        int result = imageName.hashCode();
        result = 31 * result + posted.hashCode();
        result = 31 * result + imageAuthorId.hashCode();
        result = 31 * result + (publicity ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", posted=" + posted +
                ", imageAuthorId=" + imageAuthorId +
                ", publicity=" + publicity +
                '}';
    }
}
