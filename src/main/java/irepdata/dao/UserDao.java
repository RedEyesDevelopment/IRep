package irepdata.dao;

import irepdata.model.User;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface UserDao {
    public boolean createUser();
    public boolean deleteUser();
    public boolean updateUser();
    public User getUserById(Long id);
    public User getUserAndIdeasById(Long id);
    public Set<User> getUserList();
    public SortedSet<User> getSortedUserList(boolean ascend);
    public SortedSet<User> getSortedUserListWithoutDisabled(boolean ascend);
}
