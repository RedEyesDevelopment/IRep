import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.Tag;
import irepdata.service.TagService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Gvozd on 13.11.2016.
 */
public class DBTagTest {
    private static final String ROOTCONTEXT = new String("DaoServiceTestResources/test-tags-spring-root-context.xml");
    @Test
    @SuppressWarnings("resource")
    public void TestTagFindById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        TagService service = (TagService) appContext.getBean("tagService");
        Long searchableId = 5L;
        Tag tag = service.getTagById(searchableId);
        System.out.println("Tag with id=" + searchableId + " is " + tag.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestFindAllTags() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        TagService service = (TagService) appContext.getBean("tagService");
        List<Tag> set = service.getSortedTagList("id", true);
        for (Tag tag : set) System.out.println(tag);
    }

    @Test
    @SuppressWarnings("resource")
    public void TestCreateTag() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        TagService service = (TagService) appContext.getBean("tagService");
        Tag tag = new Tag();
        tag.setContent("ножки");

        service.createTag(tag);
        System.out.println(tag.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestDeleteTag() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        TagService service = (TagService) appContext.getBean("tagService");
        Long searchableId = 2L;
        System.out.println(service.deleteTag(searchableId));
    }

    @Test
    @SuppressWarnings("resource")
    public void TestUpdateTag() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        TagService service = (TagService) appContext.getBean("tagService");
        Long searchableId = new Long(3);
        System.out.println(service.updateTag(searchableId, "testablecontent"));
    }

    @Test
    @SuppressWarnings("resource")
    public void getTagAndIdeasById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        TagService service = (TagService) appContext.getBean("tagService");
        Long searchableId = 1L;
        Tag tag = service.getTagAndIdeasById(searchableId);
        System.out.println("Tag with id=" + searchableId + " is " + tag.toString());
        for (Idea idea: tag.getIdeas()){
            System.out.println(idea.toString());
        }
    }
}
