package xyz.libris.api.security;

import com.google.common.collect.ImmutableList;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.libris.api.user.User;
import xyz.libris.api.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: [" + username + "] not found.");
        }

        // you can override user details as necessary.
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                ImmutableList.of(new SimpleGrantedAuthority("USER")));
    }
}
