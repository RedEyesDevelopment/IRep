package irepdata.controller;

import irepdata.model.User;
import irepdata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    @Autowired
    private ImageService imageService;

    // MAIN PAGE
    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    // REDIRECT TO MAIN PAGE
    @RequestMapping("/")
    public String homeSlash() {
        return "redirect:/index";
    }

    //LOGIN METHOD
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userAutorization(String login, String password, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("login:"+login+" pass:"+password);
        if (login != null && password != null) {
            User userToAutorize = userService.getUserByLogin(login);
            if (userToAutorize != null) {
                if ((userToAutorize.isEnabled()) && (userToAutorize.getPassword().equals(password))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGGEDIN_USER", userToAutorize.getUsername());
                    session.setAttribute("USER_ID", userToAutorize.getId());
                    session.setAttribute("IS_ADMIN", userToAutorize.isAdmin());
                    session.setMaxInactiveInterval(20*60);
                    return "redirect:/ideas/list";
                }
            }
        }
        return "index";
    }

    //LOGOUT METHOD
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGGEDIN_USER");
        return "redirect:/index";
    }

}
