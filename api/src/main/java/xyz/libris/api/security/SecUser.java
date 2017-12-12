package xyz.libris.api.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecUser extends User {
    private final String uniqueId;

    public SecUser(String username, String uniqueId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
