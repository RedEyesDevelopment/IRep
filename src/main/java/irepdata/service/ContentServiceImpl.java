package irepdata.service;

import irepdata.dao.ContentDao;
import irepdata.model.Content;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Gvozd on 27.11.2016.
 */
@Service
public class ContentServiceImpl implements ContentService {
    private final static Log logger = LogFactory.getLog(ContentServiceImpl.class);

    @Autowired
    private ContentDao contentDao;

    public ContentDao getContentDao() {
        return contentDao;
    }

    public void setContentDao(ContentDao contentDAO) {
        this.contentDao = contentDAO;
    }

    @Transactional
    public Content getContent(Long id) {
        return contentDao.getContent(id);
    }

    @Transactional
    public void createContent(Content content) {
        contentDao.createContent(content);
    }

//    @Transactional
//    public boolean deleteContent(Long ideaId) {
//        return contentDao.deleteContent(ideaId);
//    }

    @Transactional
    public boolean updateContent(Long ideaId, String content) {
        return contentDao.updateContent(ideaId, content);
    }
}
