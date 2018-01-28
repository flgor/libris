package xyz.libris.api.facebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import xyz.libris.api.facebook.dto.FacebookOauthAccessTokenDto;
import xyz.libris.api.facebook.dto.FacebookUserDetailsDto;
import xyz.libris.api.security.SecurityService;
import xyz.libris.api.user.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FacebookCallbackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FacebookConfig facebookConfig;
    private final FacebookService facebookService;
    private final SecurityService securityService;

    public FacebookCallbackController(FacebookConfig facebookConfig, FacebookService facebookService, SecurityService securityService) {
        this.facebookConfig = facebookConfig;
        this.facebookService = facebookService;
        this.securityService = securityService;
    }

    @GetMapping("/f/callback")
    public String onCallback(HttpServletRequest request) {
        String code = request.getParameter("code");

        try {
            FacebookOauthAccessTokenDto accessTokenDto = new FacebookOauthAccessTokenDeterminer(facebookConfig, code).determine();
            log.info("tokenResponse: [" + accessTokenDto.getAccessToken() + "].");

            FacebookUserDetailsDto facebookProfile = new FacebookUserProfileGetter(accessTokenDto.getAccessToken()).get();
            User user = facebookService.getOrCreateUser(facebookProfile);

            securityService.autoLogin(user, request);
        } catch (HttpClientErrorException ex) {
            log.error("Facebook call: [" + ex.getMessage() + "]. Response body: [" + ex.getResponseBodyAsString() + "].");
            return "redirect:/login?error";
        }

        return "redirect:/dashboard";
    }
}
