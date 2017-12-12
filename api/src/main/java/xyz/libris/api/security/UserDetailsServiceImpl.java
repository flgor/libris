package xyz.libris.api.security;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.libris.api.user.User;
import xyz.libris.api.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            log.info("User with username: [" + username + "] not found.");
            throw new UsernameNotFoundException("User with email: [" + username + "] not found.");
        }

        // you can override user details as necessary.
        return new SecUser(user.getEmail(),
                user.getUniqueId(),
                user.getPassword(),
                ImmutableList.of(new SimpleGrantedAuthority("USER")));
    }
}
