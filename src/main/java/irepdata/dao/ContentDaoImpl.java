package irepdata.dao;

import irepdata.model.Content;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Gvozd on 27.11.2016.
 */
@Repository
public class ContentDaoImpl implements ContentDao {
    private final static Log logger = LogFactory.getLog(ContentDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Content getContent(String parameterType, Long id) {
        String hql = "select distinct co from Content co where co."+ parameterType+" = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
        return (Content) query.uniqueResult();
    }

    @Override
    public void createContent(Content content) {
        sessionFactory.getCurrentSession().saveOrUpdate(content);
        logger.info("Content for idea saved with id: " + content.getId());
    }

    @Override
    public boolean deleteContent(Long ideaId) {
        String hql = "DELETE FROM Content " +
                "WHERE id = :idea_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idea_id", ideaId);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    @Override
    public boolean updateContent(String content, Long ideaId) {
        String hql = "UPDATE Content set "+
                "contentData = :content "+
                "WHERE id = :idea_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("content", content);
        query.setParameter("idea_id", ideaId);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }
}
