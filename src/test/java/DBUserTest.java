import irepdata.model.Idea;
import irepdata.model.User;
import irepdata.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import java.util.List;
import java.util.Set;

/**
 * Created by Gvozd on 13.11.2016.
 */
public class DBUserTest {
    private static final String ROOTCONTEXT = new String("DaoServiceTestResources/test-users-spring-root-context.xml");
    @Test
    @SuppressWarnings("resource")
    public void TestFindById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        Long searchableId = 1L;
        User user = service.getUserById(searchableId);
        System.out.println("User with id=" + searchableId + " is " + user.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestFindAllUsers() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        List<User> set = service.getSortedUserList("id", false);
        for (User user : set) System.out.println(user);
    }

    @Test
    @SuppressWarnings("resource")
    public void TestFindAllEnabledUsers() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        List<User> set = service.getEnabledSortedUserList("id", true);
        for (User user : set) System.out.println(user);
    }

    @Test
    @SuppressWarnings("resource")
    public void TestCreateUser() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        User user = new User();
        user.setAdmin(true);
        user.setEnabled(true);
        user.setLogin("TestAdmin");
        user.setPassword("password1");
        user.setUsername("Я_админ");

        service.createUser(user);
        System.out.println(user.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestDeleteUser() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        Long searchableId = 7L;
        System.out.println(service.deleteUser(searchableId));
    }

    @Test
    @SuppressWarnings("resource")
    public void TestUpdateUser() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        Long searchableId = new Long(6);
        System.out.println(service.updateUser(searchableId, "testadmin", "Fuck", "testpassword", true, false));
    }

    @Test
    @SuppressWarnings("resource")
    public void getUserAndIdeasById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        UserService service = (UserService) appContext.getBean("userService");
        Long searchableId = 1L;
        User user = service.getUserById(searchableId);
        System.out.println("User with id=" + searchableId + " is " + user.toString());
        for (Idea idea: user.getIdeas()){
            System.out.println(idea.toString());
        }
    }
}
