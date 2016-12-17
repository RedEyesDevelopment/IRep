package irepdata.service;

import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 15.11.2016.
 */
public interface IdeaService {
    public Idea getIdeaById(Long id);
    public Idea getIdeaWithAllDataById(Long id);
    public void createIdea(String name, String description, String image, Set<Tag> tags, User author, String content);
    public void createIdeaWithTags(String name, String description, String image, String tags, User author, String content, TagService tagService);
    public boolean deleteIdea(Long id);
    public void updateIdea(Long id, String name, String description, String image, Set<Tag> tags, boolean enabled, Long contentId, String content);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListByUsername(boolean ascend);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter);
    public void like(Long id);
    public void dislike(Long id);
    public void watch(Long id);
}
