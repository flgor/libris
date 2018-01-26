package xyz.libris.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import xyz.libris.api.user.User;
import xyz.libris.api.user.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public SecurityService(UserRepository userRepository,
                           UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public User getCurrentUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetails instanceof SecUser)) {
            log.error("Failed to deserialize spring security session.");
            return null;
        }

        SecUser secUser = (SecUser) userDetails;
        return userRepository.findByUniqueId(secUser.getUniqueId());
    }

    public void autoLogin(User user, HttpServletRequest request) {
        request.getSession();
        String username = user.getEmail();

        UserDetails principal = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        token.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(token);

        log.info("User: [" + username + "] auto logged in.");
    }
}
