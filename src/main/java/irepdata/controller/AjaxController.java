package irepdata.controller;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.model.Idea;
import irepdata.service.*;
import irepdata.views.AjaxResponseBody;
import irepdata.views.IdeaSortCriteria;
import irepdata.views.JSONViews;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Gvozd on 04.12.2016.
 */
@Controller
public class AjaxController {
    public static final String PREFIX = "/ajaxapi/";
    private final static Logger logger = Logger.getLogger(AjaxController.class);

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

    @JsonView(JSONViews.List.class)
    @ResponseBody
    @RequestMapping(value = PREFIX + "sortenabledideas")
    public AjaxResponseBody getSearchResultViaAjax(@RequestBody IdeaSortCriteria ideaSortCriteria) {
        logger.info("InAJAX controller");
        AjaxResponseBody result = new AjaxResponseBody<Idea>();

        if (ideaSortCriteria.isValid()) {
            List<Idea> ideas = ideaService.getSortedIdeaListWithoutDisabled(ideaSortCriteria.isAscend(), ideaSortCriteria.getOrderingParameter());
            if (ideas.size() > 0) {
                result.setCode("200");
                result.setMsg("");
                result.setResult(ideas);
            } else {
                result.setCode("204");
                result.setMsg("Database has no ideas!");
            }

        } else {
            result.setCode("400");
            result.setMsg("Search criteria is empty!");
        }
        return result;
    }
}
