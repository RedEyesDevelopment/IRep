package irepdata.dao;

import irepdata.model.Tag;

import java.util.List;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface TagDao {
    public Tag getTagById(Long id);
    public Tag getTagAndIdeasById(Long id);
    public void createTag(Tag tag);
    public boolean updateTag(Long id, String content);
    public boolean deleteTag(Long id);
    public List<Tag> getSortedTagList(String orderingParameter, boolean ascend);
    public List<Tag> getTagListWithIdeaId(String conditionValue);
}
