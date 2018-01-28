package xyz.libris.api.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FacebookConfig {

    private final String appId;

    private final String appSecret;

    private final String baseUrl;

    public FacebookConfig(@Value("${facebook.app.id}") String appId,
                          @Value("${facebook.app.secret}") String appSecret,
                          @Value("${libris.base.url}") String baseUrl) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.baseUrl = baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
