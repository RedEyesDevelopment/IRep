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
    public boolean updateTag(Long id, String content, boolean isEnabled);
    public boolean deleteTag(Long id);
    public List<Long> createTags(List<String> tagsData);
    public List<Tag> getSortedTagList(String orderingParameter, boolean ascend, boolean withoutDisabled);
    public List<Tag> getTagListWithIdeaId(Long ideaId, boolean withoutDisabled);
}
