import irepdata.model.Idea;
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
    private static final String ROOTCONTEXT = new String("DaoServiceTestResources/test-ideas-spring-root-context.xml");

    @Test
    @SuppressWarnings("resource")
    public void TestFindIdeaById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 1L;
        Idea idea = service.getIdeaById(searchableId);
        System.out.println(idea.toString());
    }


    @Test
    @SuppressWarnings("resource")
    public void TestFindAllIdeas() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        List<Idea> set = service.getSortedIdeaList(true, "id");
        for (Idea idea : set) System.out.println(idea);
    }

    @Test
    @SuppressWarnings("resource")
    public void TestCreateIdea() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");

        Idea idea = new Idea();
        idea.setDescription("описание");

        service.createIdea(idea);
        System.out.println(idea.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestDeleteIdea() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 3L;
        System.out.println(service.deleteIdea(searchableId));
    }

    @Test
    @SuppressWarnings("resource")
    public void TestUpdateIdea() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = new Long(4);
        System.out.println(service.updateIdea(searchableId, "testablecontent"));
    }

    @Test
    @SuppressWarnings("resource")
    public void getIdeaAndIdeasById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 1L;
        Idea idea = service.getIdeaAndIdeasById(searchableId);
        System.out.println("Idea with id=" + searchableId + " is " + idea.toString());
        for (Idea idea: idea.getIdeas()){
            System.out.println(idea.toString());
        }
    }

}
