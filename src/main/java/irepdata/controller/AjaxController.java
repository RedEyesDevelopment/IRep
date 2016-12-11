package irepdata.controller;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.model.Idea;
import irepdata.service.*;
import irepdata.views.IdeaSortCriteria;
import irepdata.views.JSONViews;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = PREFIX + "sortenabledideas", method= RequestMethod.POST)
    public ResponseEntity getSearchResultViaAjax(@RequestBody IdeaSortCriteria ideaSortCriteria) {
        logger.info("InAJAX controller");
        ResponseEntity resulting;

        if (ideaSortCriteria.isValid()) {
            List<Idea> ideas = ideaService.getSortedIdeaListWithoutDisabled(ideaSortCriteria.isAscend(), ideaSortCriteria.getOrderingParameter());
            if (ideas.size() > 0) {
                resulting = new ResponseEntity(ideas, HttpStatus.OK);
            } else {
                resulting = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } else {
            resulting = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return resulting;
    }
}
