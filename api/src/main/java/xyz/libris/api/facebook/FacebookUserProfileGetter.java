package xyz.libris.api.facebook;


import org.springframework.web.client.RestTemplate;
import xyz.libris.api.facebook.dto.FacebookUserDetailsDto;

public class FacebookUserProfileGetter {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String authToken;

    public FacebookUserProfileGetter(String authToken) {
        this.authToken = authToken;
    }

    public FacebookUserDetailsDto get() {
        String userProfileUrl = "https://graph.facebook.com/v2.11/me?access_token=" + authToken + "&fields=name,email,friends";
        FacebookUserDetailsDto resultDto = restTemplate.getForObject(userProfileUrl, FacebookUserDetailsDto.class);
        return resultDto;
    }
}
