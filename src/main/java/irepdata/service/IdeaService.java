package irepdata.service;

import irepdata.model.Idea;

import java.util.List;

/**
 * Created by Admin on 15.11.2016.
 */
public interface IdeaService {
    public Idea getIdeaById(Long id);
    public Idea getIdeaWithAllDataById(Long id);
    public void createIdea(Idea idea);
    public void deleteIdea(Long id);
    public void updateIdea(Long id, String name, String description, String image, String tags, boolean enabled, Long contentId, String content);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListByUsername(boolean ascend);
    public List<Idea> getIdeaListForTagsCloud(Long tagId, boolean onlyEnabled);
    public List<Idea> getSortedIdeaListForUser(Long userId, boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter);
    public void like(Long id);
    public void dislike(Long id);
    public void watch(Long id);
}
