package irepdata.dao;

import irepdata.model.User;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface UserDao {
    public boolean createUser(User user);
    public boolean deleteUser(Long id);
    public boolean updateUser(Long id, String login, String username, String password, boolean isAdmin, boolean isEnabled);
    public User getUserById(Long id);
    public User getUserAndIdeasById(Long id);
    public List<User> getSortedUserList(String orderingParameter, boolean ascend);
    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend);
}
