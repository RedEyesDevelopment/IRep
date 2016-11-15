package irepdata.dao;

import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Gvozd on 26.03.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private final static Log logger = LogFactory.getLog(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public UserDaoImpl() {
    }

    public void createUser(User user) {
        sessionFactory.openSession().saveOrUpdate(user);
        logger.info("User saved with id: " + user.getId());
    }

    public boolean deleteUser(Long id) {
        String hql = "DELETE FROM User " +
                "WHERE id = :user_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("user_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
}

    public boolean updateUser(Long id, String login, String username, String password, boolean isAdmin, boolean isEnabled) {
        String hql = "UPDATE User set "+
                "login = :login, " +
                "username = :username, " +
                "password = :password, " +
                "admin = :admin, " +
                "enabled = :enabled " +
                "WHERE id = :user_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("login", login);
        query.setParameter("username", username);
        query.setParameter("password", password);
        query.setParameter("admin", isAdmin);
        query.setParameter("enabled", isEnabled);
        query.setParameter("user_id", id);
        int result = query.executeUpdate();
        if (result>0){
            return true;
        } else return false;
    }

    public User getUserById(Long id) {
        User user = (User) sessionFactory.openSession().getNamedQuery("User.findById").setParameter("id", id).uniqueResult();
        sessionFactory.getCurrentSession().clear();
        return user;
    }

    public User getUserAndIdeasById(Long id) {
        return (User) sessionFactory.openSession().getNamedQuery("User.getUserAndIdeasById").uniqueResult();
    }


    public List<User> getSortedUserList(String orderingParameter, boolean ascend) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("from User c order by c." + orderingParameter + " " + order).list();
    }

    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend) {
        String order;
        if (ascend) {
            order = "asc";
        } else order = "desc";
        return sessionFactory.getCurrentSession().createQuery("from User c where c.enabled = true order by c." + orderingParameter + " " + order).list();
    }
}