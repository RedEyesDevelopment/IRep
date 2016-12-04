package irepdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gvozd on 04.12.2016.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    @RequestMapping("/")
    public String homeSlash() {
        return "redirect:/index";
    }
}
