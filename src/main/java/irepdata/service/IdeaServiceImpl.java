package irepdata.service;

import irepdata.dao.IdeaDao;
import irepdata.model.Idea;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Idea> TEMPORARY(Long id) {
        return ideaDao.TEMPORARY(id);
    }
}
