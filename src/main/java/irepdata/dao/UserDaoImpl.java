package irepdata.dao;

import irepdata.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Gvozd on 26.03.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {

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
        User user = (User) sessionFactory.getCurrentSession().createQuery("select distinct c from User c where c.id=:id").setParameter("id", id).uniqueResult();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = (User) sessionFactory.getCurrentSession().createQuery("select distinct c from User c where c.login=:login").setParameter("login", login).uniqueResult();
        return user;
    }

    public User getUserAndIdeasById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct u from User u left join fetch u.ideas i where u.id = :user_id").setParameter("user_id", id);
        return (User) query.uniqueResult();
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