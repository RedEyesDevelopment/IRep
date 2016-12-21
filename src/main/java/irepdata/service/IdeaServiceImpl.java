package irepdata.service;

import irepdata.dao.IdeaDao;
import irepdata.model.Idea;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 15.11.2016.
 */
@Service
public class IdeaServiceImpl implements IdeaService {
    private final static Logger logger = Logger.getLogger(IdeaServiceImpl.class);

    @Autowired
    private IdeaDao ideaDao;

    public IdeaDao getIdeaDao() {
        return ideaDao;
    }

    public void setIdeaDao(IdeaDao ideaDAO) {
        this.ideaDao = ideaDAO;
    }

    @Transactional
    public Idea getIdeaById(Long id) {
        return ideaDao.getIdeaById(id);
    }

    @Transactional
    public Idea getIdeaWithAllDataById(Long id) {
        return ideaDao.getIdeaWithAllDataById(id);
    }

    @Transactional
    public void createIdea(Idea idea) {
        ideaDao.createIdea(idea);
    }

    @Transactional
    public boolean deleteIdea(Long id) {
        return ideaDao.deleteIdea(id);
    }

    @Transactional
    public void updateIdea(Long id, String name, String description, String image, String tags, boolean enabled, Long contentId, String content) {
        ideaDao.updateIdea(id, name, description, image, tags, enabled);
        ideaDao.updateIdeaContent(contentId, content);
    }

    @Transactional
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter) {
        return ideaDao.getSortedIdeaList(ascend, orderingParameter);
    }

    @Transactional
    public List<Idea> getSortedIdeaListByUsername(boolean ascend) {
        return ideaDao.getSortedIdeaListByUsername(ascend);
    }

    @Transactional
    public List<Idea> getSortedIdeaListForUser(Long userId, boolean ascend, String orderingParameter) {
        return ideaDao.getSortedIdeaListForUser(userId, ascend, orderingParameter);
    }

    @Transactional
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter) {
        return ideaDao.getSortedIdeaListWithoutDisabled(ascend, orderingParameter);
    }

    @Transactional
    public void like(Long id) {
        ideaDao.like(id);
    }

    @Transactional
    public void dislike(Long id) {
        ideaDao.dislike(id);
    }

    @Transactional
    public void watch(Long id) {
        ideaDao.watch(id);
    }
}
