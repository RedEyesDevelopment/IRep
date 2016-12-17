package irepdata.controller;

import irepdata.model.Idea;
import irepdata.model.User;
import irepdata.service.*;
import irepdata.dto.IdeaDummy;
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
import java.util.Map;

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
        ideaService.createIdeaWithTags(ideaDummy.getName(),ideaDummy.getDescription(),ideaDummy.getImage(), ideaDummy.getTags(), author, ideaDummy.getContent(), tagService);
        return "redirect:/list";
    }

}