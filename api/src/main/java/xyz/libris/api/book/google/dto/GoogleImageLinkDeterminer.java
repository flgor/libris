package xyz.libris.api.book.google.dto;

import xyz.libris.api.book.google.client.data.GoogleImageLink;

public class GoogleImageLinkDeterminer {

    private final GoogleImageLink imageLink;

    public GoogleImageLinkDeterminer(GoogleImageLink imageLink) {
        this.imageLink = imageLink;
    }

    public String getThumbnail() {
        return imageLink.getThumbnail();
    }

    public String getSmallThumbnail() {
        return imageLink.getSmallThumbnail();
    }
}
