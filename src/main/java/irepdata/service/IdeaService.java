package irepdata.service;

import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 15.11.2016.
 */
@Service
public interface IdeaService {
    public Idea getIdeaById(Long id);
    public Idea getIdeaWithAllDataById(Long id);
    public void createIdea(Idea idea);
    public boolean deleteIdea(Long id);
    public boolean updateIdea(Long id, String name, String description, Set<Tag> tags, String content, boolean enabled);
    public boolean updateIdeaByAdmin(Long id, String name, String description, Set<Tag> tags, String content, int rating, User author, Timestamp viewed, Long viewedCount, boolean enabled);
    public List<Idea> getSortedIdeaList(boolean ascend, String orderingParameter);
    public List<Idea> getSortedIdeaListWithoutDisabled(boolean ascend, String orderingParameter);
}
