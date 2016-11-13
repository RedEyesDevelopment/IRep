package irepdata.dao;

import irepdata.model.Comment;
import irepdata.model.Idea;
import irepdata.model.User;

import java.util.SortedSet;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface CommentDao {
    public Comment getCommentById(Long id);
    public boolean createComment();
    public boolean updateComment();
    public boolean deleteComment();
    public SortedSet<Comment> getCommentsByUser(User user);
    public SortedSet<Comment> getSortedCommentsByUser(User user, boolean ascend);
    public SortedSet<Comment> getSortedCommentsWithoutDisabledByUser(User user, boolean ascend);
    public SortedSet<Comment> getSortedCommentsByIdea(Idea idea, boolean ascend);
    public SortedSet<Comment> getSortedCommentsWithoutDisabledByIdea(Idea idea, boolean ascend);

}
