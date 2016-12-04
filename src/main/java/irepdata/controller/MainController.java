package irepdata.controller;

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

import java.util.Map;

/**
 * Created by Gvozd on 04.12.2016.
 */

@Controller
public class MainController{
    public static final String URLCLASSPREFIX = "/ideas" ;
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

    @RequestMapping("/list")
    public String listPlaces(Map<String, Object> map) {
        map.put("placeList", placeService.listPlace());

        return "places";
    }

    @RequestMapping(URLCLASSPREFIX+"/")
    public String homeSlash() {
        return "redirect:"+URLCLASSPREFIX+"/list";
    }



    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/adding")
    public String adding() {
        Place place = new Place();
        return "adding";
    }

    @RequestMapping(value = "/adding2Handler", method = RequestMethod.POST)
    public String addingPlace(@ModelAttribute("place") Place place,
                              BindingResult result) {

        placeService.addPlace(place);

        return "redirect:/list";
    }

    @RequestMapping(value = "/select/{placeId}")
    public String selectPlace(@PathVariable("placeId") Integer placeId, Map<String, Object> map) {
        Place place = placeService.getPlace(placeId);
        logger.info(place.getPlacename() + " in maincontroller - loaded!");
        map.put("searchable", place);
        return "place";
    }

    @RequestMapping("/delete/{placeId}")
    public String deletePlace(@PathVariable("placeId") Integer placeId) {

        placeService.removePlace(placeId);

        return "redirect:/list";
    }
}