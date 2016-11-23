package irepdata.service;

import irepdata.dao.IdeaDao;
import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 15.11.2016.
 */
@Service
public class IdeaServiceImpl implements IdeaService {
    private final static Log logger = LogFactory.getLog(IdeaServiceImpl.class);

    @Autowired
    private IdeaDao ideaDao;

    public IdeaDao getIdeaDao() {
        return ideaDao;
    }

    public void setIdeaDao(IdeaDao TagDAO) {
        ideaDao = TagDAO;
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
    public void updateIdea(Long id, String name, String description, Set<Tag> tags, String content, boolean isEnabled) {
        ideaDao.updateIdea(id, name, description, tags, content, isEnabled);
    }

    @Transactional
    public boolean updateIdeaByAdmin(Long id, String name, String description, Set<Tag> tags, String content, int rating, User author, Long viewedCount, boolean enabled) {
        return ideaDao.updateIdeaByAdmin(id, name, description, tags, content, rating, author, viewedCount, enabled);
    }

    @Transactional
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter) {
        return ideaDao.getSortedIdeaList(ascend, orderingParameter);
    }

    @Transactional
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter) {
        return ideaDao.getSortedIdeaListWithoutDisabled(ascend, orderingParameter);
    }
}
