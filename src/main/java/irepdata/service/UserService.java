package irepdata.service;

import irepdata.model.User;

import java.util.List;
import java.util.SortedSet;

/**
 * Created by Admin on 14.11.2016.
 */
public interface UserService {
    public boolean createUser(User user);
    public boolean deleteUser(User user);
    public boolean updateUser(User user);
    public User getUserById(Long id);
    public User getUserAndIdeasById(Long id);
    public List<User> getSortedUserList(String orderingParameter, boolean ascend);
    public List<User> getEnabledSortedUserList(String orderingParameter, boolean ascend);
}
