package irepdata.service;

import irepdata.dao.TagDao;
import irepdata.model.Tag;
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
public class TagServiceImpl implements TagService {
    private final static Log logger = LogFactory.getLog(TagServiceImpl.class);

    @Autowired
    private TagDao tagDao;

    public TagDao getTagDao() {
        return tagDao;
    }

    public void setTagDao(TagDao TagDAO) {
        tagDao = TagDAO;
    }

    @Transactional
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Transactional
    public Tag getTagAndIdeasById(Long id) {
        return tagDao.getTagAndIdeasById(id);
    }

    @Transactional
    public void createTag(Tag tag) {
        tagDao.createTag(tag);
    }

    @Transactional
    public boolean updateTag(Long id, String content, boolean isEnabled) {
        return tagDao.updateTag(id, content, isEnabled);
    }
    @Transactional
    public boolean deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Transactional
    public List<Tag> getSortedTagList(String orderingParameter, boolean ascend, boolean withoutDisabled) {
        return tagDao.getSortedTagList(orderingParameter, ascend, withoutDisabled);
    }

    @Transactional
    public List<Tag> getTagListWithIdeaId(String ideaId, boolean withoutDisabled) {
        return tagDao.getTagListWithIdeaId(ideaId, withoutDisabled);
    }
}
