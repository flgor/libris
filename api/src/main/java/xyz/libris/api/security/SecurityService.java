package xyz.libris.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.libris.api.user.User;
import xyz.libris.api.user.UserRepository;

@Component
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    public SecurityService(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetails instanceof SecUser)) {
            return null;
        }

        SecUser secUser = (SecUser) userDetails;
        return userRepository.findByUniqueId(secUser.getUniqueId());
    }

    public void autoLogin(String username, String password) {
        // This can be done from frontend.

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        logger.debug(String.format("Auto login %s successfully!", username));
    }
}
