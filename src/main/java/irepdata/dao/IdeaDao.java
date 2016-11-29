package irepdata.dao;

import irepdata.model.Content;
import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface IdeaDao {
    public Idea getIdeaById(Long id);
    public Idea getIdeaWithAllDataById(Long id);
    public void createIdea(Idea idea, Content content);
    public boolean deleteIdea(Long id);
    public void updateIdea(Long id, String name, String description, String image, Set<Tag> tags, boolean enabled);
    public boolean updateIdeaByAdmin(Long id, String name, String description, String image, Set<Tag> tags, User author, Long viewedCount, int liked, int disliked, boolean enabled);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter);
    public void like(Long id);
    public void dislike(Long id);
    public void watch(Long id);
}
