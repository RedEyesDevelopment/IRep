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
    public void createIdea(Idea idea);
    public boolean deleteIdea(Long id);
    public void updateIdea(Long id, String name, String description, String image, Set<Tag> tags, boolean enabled);
    public boolean updateIdeaByAdmin(Long id, String name, String description, String image, Set<Tag> tags, User author, Long viewedCount, int liked, int disliked, boolean enabled);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter);
}
