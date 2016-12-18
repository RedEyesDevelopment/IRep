package irepdata.controller;

import irepdata.dto.IdeaDummy;
import irepdata.model.Content;
import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import irepdata.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Gvozd on 04.12.2016.
 */

@Controller
public class MainController {
    public static final String URLCLASSPREFIX = "/ideas/";
    final Log logger = LogFactory.getLog(MainController.class);

    @Autowired
    private IdeaService ideaService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ContentService contentService;

    @RequestMapping(URLCLASSPREFIX + "logout")
    public String mainToIndexLogout() {
        return "redirect:/logout";
    }

    @RequestMapping(URLCLASSPREFIX + "list")
    public String listOfIdeas(Map<String, Object> map, HttpServletRequest request) {
        request.getSession().setAttribute("NoLessFiles", true);
        map.put("ideaList", ideaService.getSortedIdeaList(true, "posted"));
        request.getSession().removeAttribute("IMAGE_OFFSET");
        return "idealistpage";
    }

    @RequestMapping(value = URLCLASSPREFIX + "/showidea/{ideaId}")
    public String selectPlace(@PathVariable("ideaId") Long ideaId, Map<String, Object> map) {
        Idea idea = ideaService.getIdeaWithAllDataById(ideaId);
        logger.info(idea.getName() + " in maincontroller - loaded!");
        map.put("searchable", idea);
        return "showidea";
    }

    @RequestMapping(URLCLASSPREFIX + "create")
    public String createIdea(@ModelAttribute("ideaData") IdeaDummy ideaDummy, BindingResult result) {
        IdeaDummy ideaData = new IdeaDummy(tagService);
        return "createidea";
    }

    @RequestMapping(value = URLCLASSPREFIX + "сreateideahandler", method = RequestMethod.POST)
    public String addingIdea(@ModelAttribute("ideaData") IdeaDummy ideaDummy,
                              BindingResult result, HttpServletRequest request) {
        Long authorId = (Long) request.getSession().getAttribute("USER_ID");
        User author = userService.getUserById(authorId);
        String delims = "[ ]+";
        String tags = ideaDummy.getTags();
        String[] tagStringList = tags.split(delims);
        List<Tag> tagList = tagService.getSortedTagList("id", true, false);
        List<Tag> resultList = new ArrayList<>();
        List<String> creatingList = new ArrayList<>();
        boolean newTagFlag = false;
        for (String searchableTag:tagStringList){
            newTagFlag = false;
            System.out.println("searcheableTag(String) is "+searchableTag);
            for (Tag tag:tagList){
                if (tag.isEnabled()){
                    if (tag.getContent().equals(searchableTag)){
                        System.out.println("Tag is equal "+tag);
                        resultList.add(tag);
                        newTagFlag = false;
                        break;
                    } else newTagFlag = true;
                    System.out.println("newTagFlag is"+newTagFlag);
                }
            }
            System.out.println("newTagFlag is"+newTagFlag);
            if (newTagFlag==true) {
                System.out.println("CreatingList add "+searchableTag);
                creatingList.add(searchableTag);
            }
        }
        Set<Tag> tagSet;
        if (!creatingList.isEmpty()) {
            tagService.createTags(creatingList);
            List<Tag> persistTagList = tagService.getTagList(creatingList);
            tagSet= new HashSet<>(persistTagList);
        } else {
           tagSet = new HashSet<>();

        }
        tagSet.addAll(resultList);

        String name = ideaDummy.getName();
        String description = ideaDummy.getDescription();
        String image = ideaDummy.getImage();
        String content = ideaDummy.getContent();
        Boolean enabled = ideaDummy.isEnabled();
        Idea idea = new Idea();
        idea.setName(name);
        idea.setDescription(description);
        idea.setTags(tagSet);
        idea.setAuthor(author);
        idea.setImage(image);
        idea.setEnabled(enabled);
        idea.setViewedCount(0L);
        idea.setPosted(new Timestamp(System.currentTimeMillis()));
        idea.setViewed(new Timestamp(System.currentTimeMillis()));
        idea.setLiked(0);
        idea.setDisliked(0);
        Content contentData = new Content();
        contentData.setContentData(content);
        idea.setContent(contentData);
        ideaService.createIdea(idea);
        return "redirect:/ideas/list";
    }

}