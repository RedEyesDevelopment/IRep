package irepdata.service;

import irepdata.dao.ImageDao;
import irepdata.model.Image;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gvozd on 13.12.2016.
 */
@Service
public class ImageServiceImpl implements ImageService {
    private final static Logger logger = Logger.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageDao imagedao;

    public ImageDao getImagedao() {
        return imagedao;
    }

    public void setImageDao(ImageDao imagedao) {
        this.imagedao = imagedao;
    }

    @Transactional
    public void createImage(Image image) {
        imagedao.createImage(image);
    }

    @Transactional
    public Image getImage(Long id) {
        return imagedao.getImage(id);
    }

    @Transactional
    public void updateImage(Long id, boolean publicity) {
        imagedao.updateImage(id, publicity);
    }

    @Transactional
    public List<Image> getImages(Long pagination, boolean canBePosted) {
        return imagedao.getImages(pagination, canBePosted);
    }

    @Transactional
    public List<Image> getImages(Long pagination, Long userId) {
        return imagedao.getImages(pagination, userId);
    }

    @Transactional
    public Long getImageCount() {
        return imagedao.getImageCount();
    }
}
