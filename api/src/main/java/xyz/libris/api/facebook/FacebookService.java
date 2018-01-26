package xyz.libris.api.facebook;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.libris.api.facebook.dto.FacebookUserDetailsDto;
import xyz.libris.api.user.User;
import xyz.libris.api.user.UserService;

@Service
@Transactional
public class FacebookService {

    private final FacebookAuthTokenRepository facebookAuthTokenRepository;
    private final UserService userService;

    public FacebookService(FacebookAuthTokenRepository facebookAuthTokenRepository, UserService userService) {
        this.facebookAuthTokenRepository = facebookAuthTokenRepository;

        this.userService = userService;
    }

    public User getOrCreateUser(FacebookUserDetailsDto facebookProfile) {
        FacebookAuthToken authToken = facebookAuthTokenRepository.findByFacebookId(facebookProfile.getId());


        if (authToken != null) {
            // Libris user has account already linked with facebook.
            return userService.findById(authToken.getId());
        }

        User user = userService.findByEmail(facebookProfile.getEmail());
        if (user != null) {
            // Libris user is linking account with facebook 1st time.
            saveFacebookAuthToken(user, facebookProfile);
            return user;
        }


        // New User.
        User newUser = userService.create(facebookProfile.getEmail(),
                facebookProfile.getName(),
                RandomStringUtils.randomAlphanumeric(15));

        // Link new user with facebook.
        saveFacebookAuthToken(newUser, facebookProfile);

        return newUser;

    }

    private void saveFacebookAuthToken(User user, FacebookUserDetailsDto facebookProfile) {
        FacebookAuthToken facebookAuthToken = new FacebookAuthToken();
        facebookAuthToken.setFacebookId(facebookProfile.getId());
        facebookAuthToken.setUserId(user.getId());
        facebookAuthTokenRepository.save(facebookAuthToken);
    }
}
