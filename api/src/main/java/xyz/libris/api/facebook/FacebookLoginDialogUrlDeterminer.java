package xyz.libris.api.facebook;


public class FacebookLoginDialogUrlDeterminer {

    private final String scopes = "public_profile,user_friends,email";
    private final FacebookConfig facebookConfig;
    private final String baseUrl;

    public FacebookLoginDialogUrlDeterminer(FacebookConfig facebookConfig, String baseUrl) {
        this.facebookConfig = facebookConfig;
        this.baseUrl = baseUrl;
    }

    public String determine() {
        return "https://www.facebook.com/v2.11/dialog/oauth?" +
                "client_id=" + facebookConfig.getAppId() + "&" +
                "redirect_uri=" + baseUrl + "/f/callback&" +
                "scope=" + scopes;
    }
}
