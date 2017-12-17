package xyz.libris.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import xyz.libris.api.user.User;
import xyz.libris.api.user.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public SecurityService(AuthenticationManager authenticationManager,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetails instanceof SecUser)) {
            return null;
        }

        SecUser secUser = (SecUser) userDetails;
        return userRepository.findByUniqueId(secUser.getUniqueId());
    }

    public void autoLogin(String username, String password, HttpServletRequest request) {
        // This can be done from frontend.

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(token);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        log.info("User: [" + username + "] auto logged in.");
    }
}
