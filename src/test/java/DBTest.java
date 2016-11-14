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
public class DBTest {
    @Test
    @SuppressWarnings("resource")
    public void TestFindById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("DaoServiceTestResources/test-spring-root-context.xml");
        UserService service = (UserService) appContext.getBean("userService");
        Long searchableId = 1L;
        User user = service.getUserById(searchableId);
        System.out.println("User with id=" + searchableId + " is " + user.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestFindAllUsers() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("DaoServiceTestResources/test-spring-root-context.xml");
        UserService service = (UserService) appContext.getBean("userService");
        List<User> set = service.getSortedUserList(false);
        for (User user: set) System.out.println(user);
    }
}
