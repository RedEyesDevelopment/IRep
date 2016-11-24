package irepdata.service;

import irepdata.model.Comment;

import java.util.List;

/**
 * Created by Admin on 24.11.2016.
 */
public interface CommentService {
    public Comment getCommentById(Long id);
    public void createComment(Comment comment);
    public void updateComment(Long id, String content, boolean isEnabled);
    public boolean deleteComment(Long id);
    public List<Comment> getSortedCommentsForIdea(Long ideaId, String orderingParameter, boolean ascend, boolean withoutDisabled);
}