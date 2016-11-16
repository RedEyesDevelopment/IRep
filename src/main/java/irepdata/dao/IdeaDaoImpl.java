package irepdata.dao;

import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Admin on 15.11.2016.
 */
@Repository
public class IdeaDaoImpl implements IdeaDao {
    private final static Log logger = LogFactory.getLog(IdeaDaoImpl.class);

    @Autowired
    private TagDao tagDao;

    public TagDao getTagDao() {
        return tagDao;
    }

    public void setTagDao(TagDao TagDAO) {
        tagDao = TagDAO;
    }

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
    public Idea getIdeaById(Long id) {
        Query query = sessionFactory.openSession().createQuery("select distinct i from Idea where i.id = :id").setParameter("id", id);
        Idea idea = (Idea) query.uniqueResult();
        return idea;
    }

    @Override
    public Idea getIdeaWithAllDataById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct i from Idea i left join fetch i.tags t left join fetch i.author a left join fetch i.comments c where id = :idea_id").setParameter("idea_id", id);
        return (Idea) query.uniqueResult();
    }

    @Override
    public void createIdea(Idea idea) {
        sessionFactory.openSession().saveOrUpdate(idea);
        logger.info("Idea saved with id: " + idea.getId());
    }

    @Override
    public boolean deleteIdea(Long id) {
        String hql = "DELETE FROM Idea " +
                "WHERE id = :idea_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idea_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    @Override
    public boolean updateIdeaByAdmin(Long id, String name, String description, Set<Tag> tags, String content, int rating, User author, Timestamp viewed, Long viewedCount, boolean enabled) {
        String hql = "UPDATE Idea set "+
                "name = :name, " +
                "description = :description, " +
                "tags =: tags" +
                "content = :content, " +
                "rating = :rating, " +
                "author = :author, " +
                "viewed = :viewed, " +
                "viewedCount = :viewedCount, " +
                "enabled = :enabled " +
                "WHERE id = :idea_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        query.setParameter("description", description);
        query.setParameter("tags", tags);
        query.setParameter("content", content);
        query.setParameter("enabled", enabled);
        query.setParameter("idea_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    @Override
    public boolean updateIdea(Long id, String name, String description, Set<Tag> tags, String content, boolean enabled) {
        String hql = "UPDATE Idea set "+
                "name = :name, " +
                "description = :description, " +
                "tags =: tags" +
                "content = :content, " +
                "enabled = :enabled " +
                "WHERE id = :idea_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        query.setParameter("description", description);
        query.setParameter("tags", tags);
        query.setParameter("content", content);
        query.setParameter("enabled", enabled);
        query.setParameter("idea_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    @Override
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("from Idea i order by i." + orderingParameter + " " + order).list();
    }

    @Override
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("from Idea i where i.enabled = true order by i." + orderingParameter + " " + order).list();
    }

    @Override
    public List<Idea> TEMPORARY(Long id) {
            return sessionFactory.openSession().getNamedQuery("Idea.findIdeaWithTags").list();

    }
}
