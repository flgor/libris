package xyz.libris.api.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.libris.api.security.SecurityService;

@Controller
public class BookPagesController {

    private final SecurityService securityService;

    public BookPagesController(SecurityService securityService) {
        this.securityService = securityService;
    }


    @RequestMapping(value = "/book/add", method = RequestMethod.GET)
    public String addBookPage(Model model) {
        model.addAttribute("user", securityService.getCurrentUser());

        return "bookadd";
    }
}
