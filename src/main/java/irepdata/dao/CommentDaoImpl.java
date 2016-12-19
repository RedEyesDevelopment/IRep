package irepdata.dao;

import irepdata.model.Comment;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Admin on 24.11.2016.
 */
@Repository
public class CommentDaoImpl implements CommentDao {

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
    public Comment getCommentById(Long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class);
        criteria.add( Restrictions.eq("id", id));
        Comment comment = (Comment) criteria.uniqueResult();
        return comment;
    }

    @Override
    public void createComment(Comment comment) {
        sessionFactory.getCurrentSession().saveOrUpdate(comment);
    }

    @Override
    public void updateComment(Long id, String content, boolean isEnabled) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = (Comment) session.get(Comment.class, id);
        comment.setContent(content);
        comment.setEnabled(isEnabled);
        session.update(comment);
    }

    @Override
    public boolean deleteComment(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = (Comment) session.get(Comment.class, id);
        session.delete(comment);
        return true;
    }

    @Override
    public List<Comment> getSortedCommentsForIdea(Long ideaId, String orderingParameter, boolean ascend, boolean withoutDisabled) {
        StringBuilder hqlbuilder = new StringBuilder("select distinct c from Comment c left join fetch c.idea i ");
        hqlbuilder.append("where i.id = " + ideaId + " ");
        if (withoutDisabled) hqlbuilder.append("and c.enabled = true ");
        hqlbuilder.append("order by c." + orderingParameter + " ");
        if (ascend) {
            hqlbuilder.append("asc");
        } else hqlbuilder.append("desc");
        return sessionFactory.getCurrentSession().createQuery(hqlbuilder.toString()).list();
    }
}
