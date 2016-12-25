package irepdata.controller;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.service.*;
import irepdata.ajaxcriterias.IdeaSortByUserCriteria;
import irepdata.ajaxcriterias.IdeaSortCriteria;
import irepdata.views.JSONViews;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //SHOW IDEAS WITHOUT DISABLED, SORT BY CRITERIA
    @JsonView(JSONViews.List.class)
    @ResponseBody
    @RequestMapping(value = PREFIX + "sortenabledideas", method= RequestMethod.POST)
    public ResponseEntity getIdeasWDSearchResultViaAjax(@RequestBody IdeaSortCriteria ideaSortCriteria) {
        ResponseEntity resulting;

        if (ideaSortCriteria.isValid()) {
            System.out.println("Criteria is valid");
            List<Idea> ideas = ideaService.getSortedIdeaListWithoutDisabled(ideaSortCriteria.isAscend(), ideaSortCriteria.getOrderingParameter(), 0L);
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

    //SHOW IDEAS WITHOUT DISABLED, GET BY TAG CLOUD AJAX
    @JsonView(JSONViews.List.class)
    @ResponseBody
    @RequestMapping(value = PREFIX + "getideasbytag/{tagId}", method= RequestMethod.POST)
    public ResponseEntity getIdeasForTagsCloudViaAjax(@PathVariable("tagId") Long tagId) {
        ResponseEntity resulting;
            System.out.println("Criteria is valid");
            List<Idea> ideas = ideaService.getIdeaListForTagsCloud(tagId, true);
            if (ideas.size() > 0) {
                resulting = new ResponseEntity(ideas, HttpStatus.OK);
            } else {
                resulting = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        return resulting;
    }

    //SHOW ALL IDEAS, SORT BY CRITERIA
    @JsonView(JSONViews.Admin.class)
    @ResponseBody
    @RequestMapping(value = PREFIX + "sortideas", method= RequestMethod.POST)
    public ResponseEntity getIdeasSearchResultViaAjax(@RequestBody IdeaSortCriteria ideaSortCriteria) {
        ResponseEntity resulting;

        if (ideaSortCriteria.isValid()) {
            List<Idea> ideas = ideaService.getSortedIdeaList(ideaSortCriteria.isAscend(), ideaSortCriteria.getOrderingParameter());
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

    //SORT IDEAS BY USERNAME, ASCEND IN CRITERIA
    @JsonView(JSONViews.Admin.class)
    @ResponseBody
    @RequestMapping(value = PREFIX + "sortideasbyuser", method= RequestMethod.POST)
    public ResponseEntity getIdeasSortByUserResultViaAjax(@RequestBody IdeaSortByUserCriteria ideaSortCriteria) {
        ResponseEntity resulting;

        if (ideaSortCriteria.isValid()) {
            List<Idea> ideas = ideaService.getSortedIdeaListByUsername(ideaSortCriteria.isAscend(), 0L);
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

    //GET TAGLIST FOR TAG CLOUD
    @JsonView(JSONViews.Admin.class)
    @ResponseBody
    @RequestMapping(value = PREFIX + "tagscloud", method= RequestMethod.POST)
    public ResponseEntity getTagsCloudViaAjax() {
        ResponseEntity resulting;

        List<Tag> tags = tagService.getSortedTagList("id", true, true);
            if (tags.size() > 0) {
                resulting = new ResponseEntity(tags, HttpStatus.OK);
            } else {
                resulting = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        return resulting;
    }
}
