import irepdata.model.Content;
import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import irepdata.service.IdeaService;
import irepdata.service.TagService;
import irepdata.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void getIdeaAndAllDataById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 1L;
        Idea idea = service.getIdeaWithAllDataById(searchableId);
        System.out.println("Idea with id=" + searchableId + " is " + idea.toStringWithAll());
    }

    @Test
    @SuppressWarnings("resource")
    public void getIdeaAndLikeIt() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 1L;
        Idea idea = service.getIdeaById(searchableId);
        idea.dislike();
        idea.like();
        idea.watch();
        System.out.println("Idea with id=" + searchableId + " is " + idea.toString());

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
        UserService uservice = (UserService) appContext.getBean("userService");
        TagService tservice = (TagService) appContext.getBean("tagService");

        Idea idea = new Idea();
        idea.setDescription("описание");
        User user = uservice.getUserById(3L);
        idea.setAuthor(user);
        System.out.println(user.toString());
        idea.setContent(new Content(idea,"fuuuuuuuu"));
        idea.setEnabled(true);
        idea.setName("testable9idea");
        Set<Tag> tags = idea.getTags();
        Tag tag = tservice.getTagById(3L);
        System.out.println(tag.toString());
        tags.add(tag);
        idea.setViewedCount(0L);


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
        TagService tservice = (TagService) appContext.getBean("tagService");
        Long searchableId = new Long(2);

        Tag tag = tservice.getTagById(5L);
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(tag);
        service.updateIdea(searchableId, "testableidea", "testdescription", "image", tags, true);
    }


    @Test
    @SuppressWarnings("resource")
    public void TestUpdateIdeaByAdmin() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        TagService tservice = (TagService) appContext.getBean("tagService");
        Long searchableId = new Long(4);

        Idea idea = service.getIdeaById(searchableId);
        Set<Tag> tags = idea.getTags();
        Tag tag = tservice.getTagById(3L);
        if (!tags.contains(tag)) tags.add(tag);
        System.out.println("Parameters: id="+searchableId+", tags="+tags.toString());
        System.out.println(service.updateIdeaByAdmin(searchableId, "testableidea", "testdescription", "testableimage", tags, idea.getAuthor(), idea.getViewedCount(), 5, 5, true));
    }

}
