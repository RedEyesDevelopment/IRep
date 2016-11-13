import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * Created by Gvozd on 13.11.2016.
 */
public class DBTest {
    @Test
    public void testDB(){
        HibernateJpaSessionFactoryBean hjsfb = new HibernateJpaSessionFactoryBean();
        SessionFactory sf = hjsfb.getObject();
        Session session = sf.openSession();
        session.beginTransaction();
    }
}
