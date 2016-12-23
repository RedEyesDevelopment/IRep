package irepdata.dao;

import irepdata.model.User;

import java.util.List;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface UserDao {
    public void createUser(User user);
    public void deleteUser(Long id);
    public void updateUser(Long id, String login, String username, String password, boolean isAdmin, boolean isEnabled);
    public User getUserById(Long id);
    public User getUserByLogin(String login);
    public User getUserAndIdeasById(Long id);
    public List<User> getSortedUserList(String orderingParameter, boolean ascend);
    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend);
}
