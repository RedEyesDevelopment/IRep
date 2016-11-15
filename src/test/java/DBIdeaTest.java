import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import irepdata.service.IdeaService;
import irepdata.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Gvozd on 13.11.2016.
 */
public class DBIdeaTest {
    @Test
    @SuppressWarnings("resource")
    public void TestFindById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("DaoServiceTestResources/test-spring-root-context.xml");
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 1L;
        List<Idea> ideas = service.TEMPORARY(searchableId);
        for (Idea idea: ideas){
            System.out.println("Idea is " + idea.toString());
            for (Tag tag:idea.getTags()){
                System.out.println(tag.toString());
            }
        }

    }

}
