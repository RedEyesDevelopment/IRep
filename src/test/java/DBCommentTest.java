import irepdata.model.Comment;
import irepdata.service.CommentService;
import irepdata.service.IdeaService;
import irepdata.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Gvozd on 13.11.2016.
 */
public class DBCommentTest{
    private static final String ROOTCONTEXT = new String("DaoServiceTestResources/test-spring-root-context.xml");

    @Test
    @SuppressWarnings("resource")
    public void TestCommentFindById() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        CommentService service = (CommentService) appContext.getBean("commentService");
        Long searchableId = 2L;
        Comment comment = service.getCommentById(searchableId);
        System.out.println("Comment with id=" + searchableId + " is " + comment.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestFindAllSortedCommentsForIdea() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        CommentService service = (CommentService) appContext.getBean("commentService");

        List<Comment> set = service.getSortedCommentsForIdea(2L, "posted", true, true);
        for (Comment comment : set) System.out.println(comment);
    }

    @Test
    @SuppressWarnings("resource")
    public void TestCreateComment() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        CommentService service = (CommentService) appContext.getBean("commentService");
        IdeaService iservice = (IdeaService) appContext.getBean("ideaService");
        UserService uservice = (UserService) appContext.getBean("userService");

        Comment comment = new Comment();
        comment.setContent("СукаДоСлёз!");
        comment.setEnabled(true);
        comment.setIdea(iservice.getIdeaById(1L));
        comment.setAuthor(uservice.getUserById(3L));

        service.createComment(comment);
        System.out.println(comment.toString());
    }

    @Test
    @SuppressWarnings("resource")
    public void TestDeleteComment() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        CommentService service = (CommentService) appContext.getBean("commentService");
        Long searchableId = 1L;
        System.out.println(service.deleteComment(searchableId));
    }

    @Test
    @SuppressWarnings("resource")
    public void TestUpdateComment() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(ROOTCONTEXT);
        CommentService service = (CommentService) appContext.getBean("commentService");
        Long searchableId = new Long(2);
        service.updateComment(searchableId, "ЭТАЖИШЭДЭВР!", true);
    }
}
