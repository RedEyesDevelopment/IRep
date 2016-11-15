package irepdata.service;

import irepdata.dao.UserDao;
import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.SortedSet;

/**
 * Created by Admin on 14.11.2016.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDao UserDAO;

    public UserDao getUserDAO() {
        return UserDAO;
    }

    public void setUserDAO(UserDao userDAO) {
        UserDAO = userDAO;
    }


    @Transactional
    public boolean createUser(User user) {
        return UserDAO.createUser(user);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return UserDAO.deleteUser(id);
    }

    @Transactional
    public boolean updateUser(Long id, String login, String username, String password, boolean isAdmin, boolean isEnabled) {
        return UserDAO.updateUser(id, login, username, password, isAdmin, isEnabled);
    }

    @Transactional
    public User getUserById(Long id) {
        return UserDAO.getUserById(id);
    }

    @Transactional
    public User getUserAndIdeasById(Long id) {
        return UserDAO.getUserAndIdeasById(id);
    }

    @Transactional
    public List<User> getSortedUserList(String orderingParameter, boolean ascend) {
        return UserDAO.getSortedUserList(orderingParameter, ascend);
    }

    @Transactional
    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend) {
        return UserDAO.getEnabledSortedUserList(orderingParameter, ascend);
    }
}
