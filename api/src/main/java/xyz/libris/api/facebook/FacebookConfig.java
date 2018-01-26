package xyz.libris.api.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FacebookConfig {

    private final String appId;

    private final String appSecret;

    public FacebookConfig(@Value("${facebook.app.id}") String appId,
                          @Value("${facebook.app.secret}") String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
