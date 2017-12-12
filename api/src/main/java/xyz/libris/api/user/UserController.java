package xyz.libris.api.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xyz.libris.api.security.SecurityService;

@RestController
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/api/v1/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserDto createUser) {
        userService.create(createUser.getEmail(), createUser.getFullName(), createUser.getPassword());
        log.info("User with email: [" + createUser.getEmail() + "] created.");

        securityService.autoLogin(createUser.getEmail(), createUser.getPassword());
        log.info("User with email: [" + createUser.getEmail() + "] auto login.");
    }


}
