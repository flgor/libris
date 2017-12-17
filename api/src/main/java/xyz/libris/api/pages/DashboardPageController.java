package xyz.libris.api.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.libris.api.book.BookService;
import xyz.libris.api.security.SecurityService;
import xyz.libris.api.user.User;

@Controller
public class DashboardPageController {

    private final BookService bookService;
    private final SecurityService securityService;

    public DashboardPageController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboardPage(Model model) {
        populateDashboardModel(model);

        return "dashboard";
    }

    private void populateDashboardModel(Model model) {
        //Todo move this into frontend logic.

        User currentUser = securityService.getCurrentUser();
        model.addAttribute("user", currentUser);

        model.addAttribute("books", bookService.getAll(currentUser));
    }
}
