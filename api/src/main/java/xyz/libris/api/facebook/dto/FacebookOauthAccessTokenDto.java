package xyz.libris.api.facebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacebookOauthAccessTokenDto {

    @JsonProperty("access_token")
    private String accessToken;

    private String bearer;

    @JsonProperty("expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
