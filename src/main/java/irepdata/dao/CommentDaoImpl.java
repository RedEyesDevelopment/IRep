package irepdata.dao;

import irepdata.model.Comment;
import irepdata.model.Idea;
import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Admin on 24.11.2016.
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    private final static Log logger = LogFactory.getLog(IdeaDaoImpl.class);

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
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct c from Comment c left join fetch c.author a left join fetch c.idea i where id = :comm_id").setParameter("comm_id", id);
        return (Comment) query.uniqueResult();
    }

    @Override
    public void createComment(Comment comment) {
        sessionFactory.getCurrentSession().saveOrUpdate(comment);
        logger.info("Comment saved with id: " + comment.getId());
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
        String hql = "DELETE FROM Comment " +
                "WHERE id = :comm_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("comm_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
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
