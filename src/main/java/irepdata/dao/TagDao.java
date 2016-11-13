package irepdata.dao;

import irepdata.model.Tag;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface TagDao {
    public Tag getTagById(Long id);
    public boolean createTag();
    public boolean updateTag();
    public boolean deleteTag();
    public Set<Tag> getTagList();
    public SortedSet<Tag> getSortedTagList(boolean ascend);
    public SortedSet<Tag> getSortedTagListWithoutDisabled(boolean ascend);
}
