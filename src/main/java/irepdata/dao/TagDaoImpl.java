package irepdata.dao;

import irepdata.model.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Admin on 15.11.2016.
 */
@Repository
public class TagDaoImpl implements TagDao {
    private final static Log logger = LogFactory.getLog(TagDaoImpl.class);

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
    public Tag getTagById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct t from Tag t where t.id = :id").setParameter("id", id);
        Tag tag = (Tag) query.uniqueResult();
        return tag;
    }

    @Override
    public Tag getTagAndIdeasById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct t from Tag t left join fetch t.ideas i where id = :tag_id").setParameter("tag_id", id);
        return (Tag) query.uniqueResult();
    }

    @Override
    public void createTag(Tag tag) {
        sessionFactory.openSession().saveOrUpdate(tag);
        logger.info("Tag saved with id: " + tag.getId());
    }

    @Override
    public boolean updateTag(Long id, String content) {
        String hql = "UPDATE Tag set " +
                "content = :content, " +
                "WHERE id = :tag_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("content", content);
        query.setParameter("tag_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    @Override
    public boolean deleteTag(Long id) {
        String hql = "DELETE FROM Tag " +
                "WHERE id = :tag_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("tag_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    @Override
    public List<Tag> getSortedTagList(String orderingParameter, boolean ascend) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("from Tag t order by t." + orderingParameter + " " + order).list();
    }
}
