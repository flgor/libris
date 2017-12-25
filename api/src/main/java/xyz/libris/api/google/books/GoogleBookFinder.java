package xyz.libris.api.google.books;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.libris.api.google.books.data.GoogleBook;
import xyz.libris.api.google.books.data.GoogleBookSearchResponse;

import java.util.List;

public class GoogleBookFinder {
    private static final String KEY = "AIzaSyC46Qna_yAqY9ru9qFkWP7-KGLyzLHwP8Y";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String query;

    public GoogleBookFinder(String query) {
        this.query = query;
    }

    public List<GoogleBook> find() {
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", query)
                .queryParam("key", KEY)
                .toUriString();

        GoogleBookSearchResponse response = restTemplate.getForObject(url, GoogleBookSearchResponse.class);
        return response.getItems();
    }
}
