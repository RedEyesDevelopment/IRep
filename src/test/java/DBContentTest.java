import irepdata.model.Content;
import irepdata.service.ContentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gvozd on 13.11.2016.
 */
public class DBContentTest {
    private static final String ROOTCONTEXT = new String("DaoServiceTestResources/test-contents-spring-root-context.xml");
    @Test
    @SuppressWarnings("resource")
    public void TestContentFindById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        ContentService service = (ContentService) appContext.getBean("contentService");
        Long searchableId = 1L;
        Content content = service.getContent(searchableId);
        System.out.println("Content with id=" + searchableId + " is " + content.toString());
    }

//    @Test
//    @SuppressWarnings("resource")
//    public void TestDeleteContent() {
//        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
//        ContentService service = (ContentService) appContext.getBean("contentService");
//        Long searchableId = 1L;
//        System.out.println(service.deleteContent(searchableId));
//    }

    @Test
    @SuppressWarnings("resource")
    public void TestUpdateContent() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        ContentService service = (ContentService) appContext.getBean("contentService");
        Long searchableId = new Long(3);
        service.updateContent(searchableId, "ЭТАЖИШЭДЭВР!");
    }
}
