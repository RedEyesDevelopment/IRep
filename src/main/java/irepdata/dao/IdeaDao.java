package irepdata.dao;

import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface IdeaDao {
    public Idea getIdeaById(Long id);
    public Idea getIdeaWithAllDataById(Long id);
    public void createIdea(Idea idea);
    public boolean deleteIdea(Long id);
    public boolean updateIdea(Long id, String name, String description, Set<Tag> tags, String content, boolean enabled);
    public boolean updateIdeaByAdmin(Long id, String name, String description, Set<Tag> tags, String content, int rating, User author, Timestamp viewed, Long viewedCount, boolean enabled);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter);
}
