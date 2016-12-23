package irepdata.service;

import irepdata.dao.CommentDao;
import irepdata.model.Comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 24.11.2016.
 */
@Service
public class CommentServiceImpl implements CommentService{
    private final static Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    public CommentDao getIdeaDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Transactional
    public void createComment(Comment comment) {
        commentDao.createComment(comment);
    }

    @Transactional
    public void updateComment(Long id, String content, boolean isEnabled) {
        commentDao.updateComment(id, content, isEnabled);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentDao.deleteComment(id);
    }

    @Transactional
    public List<Comment> getSortedCommentsForIdea(Long ideaId, String orderingParameter, boolean ascend, boolean withoutDisabled) {
        return commentDao.getSortedCommentsForIdea(ideaId, orderingParameter, ascend, withoutDisabled);
    }
}
