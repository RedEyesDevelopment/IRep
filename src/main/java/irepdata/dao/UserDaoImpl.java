package irepdata.dao;

import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
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

    public boolean createUser(User user) {
        sessionFactory.openSession().saveOrUpdate(user);
        logger.info("Contact saved with id: "+ user.getId());
        return true;
    }

    public boolean deleteUser(User user) {
        User userToDelete = getUserById(user.getId());
        if (null != userToDelete) {
            sessionFactory.getCurrentSession().delete(user);
            return true;
        } else return false;
    }

    public boolean updateUser(User user) {
        sessionFactory.getCurrentSession().setFlushMode(FlushMode.COMMIT);
        sessionFactory.getCurrentSession().setDefaultReadOnly(false);
        sessionFactory.getCurrentSession().update(user);
        return true;
    }

    public User getUserById(Long id) {
        return (User) sessionFactory.openSession().getNamedQuery("User.findById").setParameter("id", id).uniqueResult();
    }

    public User getUserAndIdeasById(Long id) {
        return (User) sessionFactory.openSession().getNamedQuery("User.getUserAndIdeasById").uniqueResult();
    }


    public List<User> getSortedUserList(boolean ascend) {
        return sessionFactory.getCurrentSession().createQuery("from User c").list();
    }

    public SortedSet<User> getSortedUserListWithoutDisabled(boolean ascend) {
        return null;
    }
}