package irepdata.service;

import irepdata.dao.UserDao;
import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.11.2016.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Transactional
    public boolean updateUser(Long id, String login, String username, String password, boolean isAdmin, boolean isEnabled) {
        return userDao.updateUser(id, login, username, password, isAdmin, isEnabled);
    }

    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public User getUserAndIdeasById(Long id) {
        return userDao.getUserAndIdeasById(id);
    }

    @Transactional
    public List<User> getSortedUserList(String orderingParameter, boolean ascend) {
        return userDao.getSortedUserList(orderingParameter, ascend);
    }

    @Transactional
    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend) {
        return userDao.getEnabledSortedUserList(orderingParameter, ascend);
    }
}
