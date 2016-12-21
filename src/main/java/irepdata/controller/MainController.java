package irepdata.controller;

import irepdata.dto.IdeaDummy;
import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.model.User;
import irepdata.service.*;
import irepdata.support.IdeaDummyConversionTool;
import irepdata.support.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    @Autowired
    private TagSupport tagSupport;

    //LOGOUT
    @RequestMapping(URLCLASSPREFIX + "logout")
    public String mainToIndexLogout() {
        return "redirect:/logout";
    }

    //LIST
    @RequestMapping(URLCLASSPREFIX + "list")
    public String listOfIdeas(Map<String, Object> map, HttpServletRequest request) {
        map.put("ideaList", ideaService.getSortedIdeaListWithoutDisabled(true, "posted"));
        request.getSession().removeAttribute("IMAGE_OFFSET");
        return "idealistpage";
    }

    //CABINET
    @RequestMapping(URLCLASSPREFIX + "cabinet")
    public String cabinet(Map<String, Object> map, HttpServletRequest request) {
        Long myId = (Long) request.getSession().getAttribute("USER_ID");
        map.put("ideaList", ideaService.getSortedIdeaListForUser(myId, true, "posted"));
        return "cabinetpage";
    }

    //SELECT IDEA
    @RequestMapping(value = URLCLASSPREFIX + "/showidea/{ideaId}")
    public String selectIdea(@PathVariable("ideaId") Long ideaId, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Idea idea = ideaService.getIdeaWithAllDataById(ideaId);
        logger.info(idea.getName() + " in maincontroller - loaded!");
        request.setAttribute("RURI", "/ideas/list");
        map.put("searchable", idea);
        return "showidea";
    }

    //SELECT OWN IDEA
    @RequestMapping(value = URLCLASSPREFIX + "/showmyidea/{ideaId}")
    public String selectMyIdea(@PathVariable("ideaId") Long ideaId, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Idea idea = ideaService.getIdeaWithAllDataById(ideaId);
        logger.info(idea.getName() + " in maincontroller - loaded!");
        request.setAttribute("RURI", "/ideas/cabinet");
        map.put("searchable", idea);
        return "showmyidea";
    }

    //CREATE IDEA
    @RequestMapping(URLCLASSPREFIX + "create")
    public String createIdea(@ModelAttribute("ideaData") IdeaDummy ideaDummy, BindingResult result) {
        IdeaDummy ideaData = new IdeaDummy();
        return "createidea";
    }

    //CREATE IDEA HANDLER
    @RequestMapping(value = URLCLASSPREFIX + "сreateideahandler", method = RequestMethod.POST)
    public String addingIdea(@ModelAttribute("ideaData") IdeaDummy ideaDummy,
                              BindingResult result, HttpServletRequest request) {
        Long authorId = (Long) request.getSession().getAttribute("USER_ID");
        User author = userService.getUserById(authorId);

        Set<Tag> tagSet = tagSupport.parseTagsFromStringToSet(ideaDummy.getTags());

        Idea idea = new Idea();
        idea.setTags(tagSet);
        idea.setAuthor(author);
        idea.setViewedCount(0L);
        idea.setPosted(new Timestamp(System.currentTimeMillis()));
        idea.setViewed(new Timestamp(System.currentTimeMillis()));
        idea.setLiked(0);
        idea.setDisliked(0);

        IdeaDummyConversionTool.fillIdeaFromDummy(ideaDummy, idea);

        ideaService.createIdea(idea);
        return "redirect:/ideas/cabinet";
    }

    //EDIT OWN IDEA
    @RequestMapping(value = URLCLASSPREFIX + "/editmyidea/{ideaId}")
    public String editMyIdea(@PathVariable("ideaId") Long ideaId, Model model,
                             HttpServletRequest request, HttpServletResponse response) {
        Idea idea = ideaService.getIdeaWithAllDataById(ideaId);

        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        Boolean isAdmin = (Boolean) request.getSession().getAttribute("IS_ADMIN");
        if (!userId.equals(idea.getAuthor().getId()) && (!isAdmin)){
            try {
                response.sendRedirect("/ideas/list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info(idea.getName() + " in maincontroller - loaded!");

        Map<String, String> isEnabled = new LinkedHashMap<String, String>();
        IdeaDummy idummy = new IdeaDummy();
        IdeaDummyConversionTool.fillDummyFromIdea(idea, idummy);
        isEnabled.put("true", "True");
        isEnabled.put("false", "False");
        model.addAttribute("ideaAttrib", idummy);
        model.addAttribute("enablind", isEnabled);
        request.getSession().setAttribute("TARGETIDEA", ideaId);
        return "editmyideapage";
    }

    //EDIT OWN IDEA HANDLER
    @RequestMapping(value = URLCLASSPREFIX + "editideahandler", method = RequestMethod.POST)
    public String editMyIdeaHandler(@ModelAttribute("ideaAttrib") IdeaDummy ideaDummy, BindingResult result,
                                    HttpServletRequest request) {
        System.out.println(ideaDummy.toString());
        Long targetIdeaId = (Long) request.getSession().getAttribute("TARGETIDEA");
        Long targetContentId = ideaService.getIdeaById(targetIdeaId).getContentId();
        ideaService.updateIdea(targetIdeaId, ideaDummy.getName(),ideaDummy.getDescription(),ideaDummy.getImage(),ideaDummy.getTags(),ideaDummy.isEnabled(),targetContentId,ideaDummy.getContent());
        return "redirect:/ideas/cabinet";
    }

}