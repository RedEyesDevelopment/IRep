package irepdata.controller;

import irepdata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Gvozd on 04.12.2016.
 */
@Controller
public class IndexController {

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



    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    @RequestMapping("/")
    public String homeSlash() {
        return "redirect:/index";
    }

    @RequestMapping("/hello")
    public String hello(Map<String, Object> map) {
            map.put("ideaList", ideaService.getSortedIdeaList(true, "id"));
        return "hello";
    }
}
