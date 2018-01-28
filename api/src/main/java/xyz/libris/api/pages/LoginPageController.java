package xyz.libris.api.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.libris.api.facebook.FacebookConfig;
import xyz.libris.api.facebook.FacebookLoginDialogUrlDeterminer;

@Controller
public class LoginPageController {

    private final FacebookConfig facebookConfig;

    public LoginPageController(FacebookConfig facebookConfig) {
        this.facebookConfig = facebookConfig;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {

        String facebookLoginUrl = new FacebookLoginDialogUrlDeterminer(facebookConfig).determine();
        model.addAttribute("facebookLoginUrl", facebookLoginUrl);

        return "login";
    }
}
