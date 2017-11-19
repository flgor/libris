package xyz.libris.api.indexhtml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexHtmlController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init() {
        return "index";
    }
}
