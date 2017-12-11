package xyz.libris.api.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinPageController {

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinPage() {
        return "join";
    }

    @RequestMapping(value = "/join_beta", method = RequestMethod.GET)
    public String joinBetaPage() {
        return "join_beta";
    }
}
