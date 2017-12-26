package xyz.libris.api.book.google.client;

import org.springframework.web.client.RestTemplate;
import xyz.libris.api.book.google.client.data.GoogleBook;

public class GoogleBookFinder {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String googleId;

    public GoogleBookFinder(String googleId) {
        this.googleId = googleId;
    }


    public GoogleBook find() {
        return restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes/{googleId}",
                GoogleBook.class,
                googleId);
    }
}
