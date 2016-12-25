package irepdata.dao;

import irepdata.model.Idea;

import java.util.List;

/**
 * Created by Gvozd on 13.11.2016.
 */
public interface IdeaDao {
    public Idea getIdeaById(Long id);
    public Idea getIdeaWithAllDataById(Long id);
    public void createIdea(Idea idea);
    public void deleteIdea(Long id);
    public void updateIdea(Long id, String name, String description, String image, String tags, boolean enabled);
    public void updateIdeaContent(Long contentId, String content);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListForUser(Long userId, boolean ascend, String orderingParameter, long pagination);
    public List<Idea> getIdeaListForTagsCloud(Long tagId, boolean onlyEnabled);
    public List<Idea> getSortedIdeaListByUsername(boolean ascend, long pagination);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter, long pagination);
    public long getIdeasCount(String filter, String value);
    public long getIdeasCount();
    public void like(Long id);
    public void dislike(Long id);
    public void watch(Long id);
}
