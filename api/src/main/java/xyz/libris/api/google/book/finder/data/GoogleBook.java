package xyz.libris.api.google.book.finder.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleBook {

    private String kind;
    @JsonProperty("id")
    private String googleId;
    private String etag;
    private GoogleVolumeInfo volumeInfo;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public GoogleVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GoogleVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
