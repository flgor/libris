package xyz.libris.api.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.libris.api.security.SecurityService;

import javax.servlet.http.HttpServletRequest;

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
    public void create(@RequestBody CreateUserDto createUser,
                       HttpServletRequest request) {
        User user = userService.create(createUser.getEmail(), createUser.getFullName(), createUser.getPassword());
        log.info("User with email: [" + createUser.getEmail() + "] created.");

        securityService.autoLogin(user, request);
        log.info("User with email: [" + createUser.getEmail() + "] auto login.");
    }

    @GetMapping("/api/v1/user/count/public")
    @ResponseBody
    public Long count() {
        return userService.count();
    }


}
