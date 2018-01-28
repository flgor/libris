package xyz.libris.api.facebook;


public class FacebookLoginDialogUrlDeterminer {

    private final String scopes = "public_profile,user_friends,email";
    private final FacebookConfig facebookConfig;

    public FacebookLoginDialogUrlDeterminer(FacebookConfig facebookConfig) {
        this.facebookConfig = facebookConfig;
    }

    public String determine() {
        return "https://www.facebook.com/v2.11/dialog/oauth?" +
                "client_id=" + facebookConfig.getAppId() + "&" +
                "redirect_uri=" + facebookConfig.getBaseUrl() + "/f/callback&" +
                "scope=" + scopes;
    }
}
