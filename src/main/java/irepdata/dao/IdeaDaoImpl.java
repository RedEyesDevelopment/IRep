package irepdata.dao;

import irepdata.model.Idea;
import irepdata.support.TagSupport;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Admin on 15.11.2016.
 */
@Repository
public class IdeaDaoImpl implements IdeaDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private TagSupport tagSupport;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Idea getIdeaById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct i from Idea i where i.id = :id").setParameter("id", id);
        Idea idea = (Idea) query.uniqueResult();
        return idea;
    }

    @Override
    public Idea getIdeaWithAllDataById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct i from Idea i left join fetch i.content co left join fetch i.tags t left join fetch i.author a left join fetch i.comments c where i.id = :idea_id").setParameter("idea_id", id);
        return (Idea) query.uniqueResult();
    }

    @Override
    public void createIdea(Idea idea) {
        sessionFactory.getCurrentSession().saveOrUpdate(idea);
    }

    @Override
    public boolean deleteIdea(Long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Idea.class);
        criteria.add(Restrictions.eq("id", id));
        Idea idea = (Idea) criteria.uniqueResult();
        sessionFactory.getCurrentSession().delete(idea);
        return true;
    }

    @Override
    public boolean updateIdeaContent(Long contentId, String content) {
        String hql = "UPDATE Content set " +
                "contentData = :content " +
                "WHERE id = :content_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("content", content);
        query.setParameter("content_id", contentId);
        int result = query.executeUpdate();
        if (result > 0) {
            return true;
        } else return false;
    }

    @Override
    public void updateIdea(Long id, String name, String description, String image, String tags, boolean isEnabled) {
        Session session = sessionFactory.getCurrentSession();
        Idea idea = (Idea) session.get(Idea.class, id);
        idea.setName(name);
        idea.setDescription(description);
        idea.setImage(image);
       tagSupport.parseTagsFromStringToSet(idea.getTags(), tags, true);
        idea.setEnabled(isEnabled);
        session.update(idea);
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
    public List<Idea> getSortedIdeaListForUser(Long userId, boolean ascend, String orderingParameter) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("select distinct i from Idea i left join fetch i.author a where a.id = " + userId + " order by i." + orderingParameter + " " + order).list();
    }

    @Override
    public List<Idea> getSortedIdeaListByUsername(boolean ascend) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("from Idea i left join fetch i.author a where i.enabled = true order by a.username " + order).list();
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
    public void like(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Idea idea = (Idea) session.get(Idea.class, id);
        idea.like();
        session.update(idea);
    }

    @Override
    public void dislike(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Idea idea = (Idea) session.get(Idea.class, id);
        idea.dislike();
        session.update(idea);
    }

    @Override
    public void watch(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Idea idea = (Idea) session.get(Idea.class, id);
        idea.setViewed(new Timestamp(System.currentTimeMillis()));
        idea.addViewedCount();
        session.update(idea);
    }
}
