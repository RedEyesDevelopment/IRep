package irepdata.service;

import irepdata.dao.CommentDao;
import irepdata.model.Comment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 24.11.2016.
 */
@Service
public class CommentServiceImpl implements CommentService{
    private final static Log logger = LogFactory.getLog(CommentServiceImpl.class);

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
    public boolean deleteComment(Long id) {
        return commentDao.deleteComment(id);
    }

    @Transactional
    public List<Comment> getSortedCommentsForIdea(Long ideaId, String orderingParameter, boolean ascend, boolean withoutDisabled) {
        return commentDao.getSortedCommentsForIdea(ideaId, orderingParameter, ascend, withoutDisabled);
    }
}
