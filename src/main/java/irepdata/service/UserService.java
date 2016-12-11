package irepdata.service;

import irepdata.model.User;

import java.util.List;

/**
 * Created by Admin on 14.11.2016.
 */
public interface UserService {
    public void createUser(User user);
    public boolean deleteUser(Long id);
    public boolean updateUser(Long id, String login, String username, String password, boolean isAdmin, boolean isEnabled);
    public User getUserById(Long id);
    public User getUserByLogin(String login);
    public User getUserAndIdeasById(Long id);
    public List<User> getSortedUserList(String orderingParameter, boolean ascend);
    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend);
}
