package irepdata.dao;

import irepdata.model.Idea;
import irepdata.support.TagSupport;
import org.hibernate.*;
import org.hibernate.criterion.Order;
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
    public void deleteIdea(Long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Idea.class);
        criteria.add(Restrictions.eq("id", id));
        Idea idea = (Idea) criteria.uniqueResult();
        sessionFactory.getCurrentSession().delete(idea);
    }

    @Override
    public void updateIdeaContent(Long contentId, String content) {
        String hql = "UPDATE Content set " +
                "contentData = :content " +
                "WHERE id = :content_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("content", content);
        query.setParameter("content_id", contentId);
        int result = query.executeUpdate();
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
    public List<Idea> getSortedIdeaListForUser(Long userId, boolean ascend, String orderingParameter, long pagination) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Idea.class);
        criteria.setMaxResults(Idea.MAXIDEASSHOWINGCAPACITY);
        criteria.setFirstResult(Math.toIntExact(pagination));
        criteria.setFetchMode("author", FetchMode.JOIN);
        criteria.setReadOnly(true);
        criteria.createAlias("author", "authorname");
        criteria.add( Restrictions.eq("authorname.id", userId));
        Order order;
        if (ascend){
            order = Order.asc(orderingParameter);
        } else order = Order.desc(orderingParameter);
        criteria.addOrder(order);
        List<Idea> result = criteria.list();
        return result;
    }

    @Override
    public List<Idea> getIdeaListForTagsCloud(Long tagId, boolean onlyEnabled) {
        return sessionFactory.getCurrentSession().createQuery("select distinct i from Idea i left join fetch i.tags t where t.id = " + tagId + " asc").list();
    }

    @Override
    public List<Idea> getSortedIdeaListByUsername(boolean ascend, long pagination) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Idea.class);
        criteria.setMaxResults(Idea.MAXIDEASSHOWINGCAPACITY);
        criteria.setFirstResult(Math.toIntExact(pagination));
        criteria.add( Restrictions.eq("enabled", true));
        criteria.setFetchMode("author", FetchMode.JOIN);
        criteria.setReadOnly(true);
        criteria.createAlias("author", "authorname");
        Order order;
        if (ascend){
            order = Order.asc("authorname.username");
        } else order = Order.desc("authorname.username");
        criteria.addOrder(order);
        List<Idea> result = criteria.list();
        return result;
    }

    @Override
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter, long pagination) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Idea.class);
        criteria.setMaxResults(Idea.MAXIDEASSHOWINGCAPACITY);
        criteria.setFirstResult(Math.toIntExact(pagination));
        criteria.add( Restrictions.eq("enabled", true));
        criteria.setFetchMode("tags", FetchMode.SELECT);
        criteria.setFetchMode("author", FetchMode.SELECT);
        criteria.setReadOnly(true);
        Order order;
        if (ascend){
            order = Order.asc(orderingParameter);
        } else order = Order.desc(orderingParameter);
        criteria.addOrder(order);
        List<Idea> result = criteria.list();
        return result;
    }

    @Override
    public long getIdeasCount(String filter, String value) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from Ideas i where i."+filter+" = "+value);
        return (Long)query.uniqueResult();
    }

    @Override
    public long getIdeasCount() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from Ideas");
        return (Long)query.uniqueResult();
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
