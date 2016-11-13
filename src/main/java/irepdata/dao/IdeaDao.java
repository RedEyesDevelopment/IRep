package irepdata.dao;

import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface IdeaDao {
    public Idea getIdeaById(Long id);
    public boolean createIdea();
    public boolean deleteIdea();
    public boolean updateIdea();
    public Set<Idea> getIdeaList();
    public Set<Idea> getIdeaListWithoutDisabled(boolean ascend, boolean byUpdated);
    public SortedSet<Idea> getSortedIdeaList(boolean ascend, boolean byUpdated);
    public SortedSet<Idea> getSortedIdeaListWithoutDisabledByTag(Tag tag);
    public SortedSet<Idea> getSortedIdeaListWithoutDisabledByUser(User user);
    public SortedSet<Idea> getSortedIdeaListWithoutDisabledByRating(boolean ascend);
}
