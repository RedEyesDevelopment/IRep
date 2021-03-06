package irepdata.dao;

import irepdata.model.Image;

import java.util.List;

/**
 * Created by Gvozd on 13.12.2016.
 */
public interface ImageDao {
    public void createImage(Image image);
    public Image getImage(Long id);
    public void updateImage(Long id, boolean publicity);
    public List<Image> getImages(Long pagination, boolean canBePosted);
    public Long getImageCount();
}
