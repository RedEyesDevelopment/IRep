package irepdata.controller;

import irepdata.model.User;
import irepdata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userAutorization(String login, String password, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("login:"+login+" pass:"+password);

        if (login != null && password != null) {
            User userToAutorize = userService.getUserByLogin(login);
            if (userToAutorize != null) {
                if (userToAutorize.getPassword().equals(password)) {
                    request.getSession().setAttribute("LOGGEDIN_USER", login);
                    System.out.println("redirect:/ideas/list");
                    return "redirect:/ideas/list";
                }
            }
        }
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGGEDIN_USER");
        return "redirect:/index";
    }

    //* Тестовая страница, для отработки всякого дерьма (Потом удалить к едрени фене)
//    @RequestMapping("/hello")
//    public String hello(Map<String, Object> map) {
//            map.put("ideaList", ideaService.getSortedIdeaList(true, "id"));
//        return "hello?login=fail";
//    }

    //* Тестовая страница, для отработки Сашей статики (Потом удалить к едрени фене)
    @RequestMapping("/static")
    public String staticPageForAlex() {
        return "static";
    }
}
