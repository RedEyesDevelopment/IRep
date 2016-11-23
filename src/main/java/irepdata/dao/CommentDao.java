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
    public SortedSet<Comment> getSortedCommentsForIdea(String ideaId, String orderingParametr, boolean ascend, boolean withoutDisabled);
}
