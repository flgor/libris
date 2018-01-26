package xyz.libris.api.facebook;


import org.springframework.web.client.RestTemplate;
import xyz.libris.api.facebook.dto.FacebookOauthAccessTokenDto;

public class FacebookOauthAccessTokenDeterminer {

    private final RestTemplate restTemplate = new RestTemplate();
    private final FacebookConfig facebookConfig;
    private final String baseUrl;
    private final String code;

    public FacebookOauthAccessTokenDeterminer(FacebookConfig facebookConfig,
                                              String baseUrl,
                                              String code) {
        this.facebookConfig = facebookConfig;
        this.baseUrl = baseUrl;
        this.code = code;
    }

    public FacebookOauthAccessTokenDto determine() {
        String loginDialogUrl = new FacebookLoginDialogUrlDeterminer(facebookConfig, baseUrl).determine();

        String oauthTokenUrl = "https://graph.facebook.com/v2.11/oauth/access_token?" +
                "client_id=" + this.facebookConfig.getAppId() +
                "&redirect_uri=" + loginDialogUrl +
                "&client_secret=" + this.facebookConfig.getAppSecret() +
                "&code=" + this.code;

        FacebookOauthAccessTokenDto accessTokenDto = restTemplate.getForObject(oauthTokenUrl, FacebookOauthAccessTokenDto.class);
        return accessTokenDto;
    }
}
