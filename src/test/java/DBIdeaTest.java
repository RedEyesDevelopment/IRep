import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import irepdata.service.ContentService;
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
public class DBIdeaTest{
    private static final String ROOTCONTEXT = new String("DaoServiceTestResources/test-spring-root-context.xml");

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
        Long searchableId = 3L;
        Idea idea = service.getIdeaWithAllDataById(searchableId);
        System.out.println("Idea with id=" + searchableId + " is " + idea.toStringWithAll());
    }

    @Test
    @SuppressWarnings("resource")
    public void getIdeaAndLikeIt() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 2L;
        service.like(searchableId);
        service.dislike(searchableId);
        service.watch(searchableId);
    }


    @Test
    @SuppressWarnings("resource")
    public void getIdeaWatchIt() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        IdeaService service = (IdeaService) appContext.getBean("ideaService");
        Long searchableId = 3L;
        service.watch(searchableId);
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
        ContentService coservice = (ContentService) appContext.getBean("contentService");

        User user = uservice.getUserById(3L);
        Set<Tag> tags = new HashSet<>();
        Tag tag = tservice.getTagById(2L);
        tags.add(tag);
        service.createIdea("ideaName", "описание", null, tags, user, "COOONTENTT");
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
        Long searchableId = new Long(1);

        Tag tag = tservice.getTagById(5L);
        Set<Tag> tags = new HashSet<Tag>();
        Long contentId = service.getIdeaById(searchableId).getContentId();
        tags.add(tag);
        service.updateIdea(searchableId, "testableidea", "testdescription", "image", tags, true, contentId, "пиздец");
    }
}
