package xyz.libris.api.google.book.finder;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.libris.api.google.book.finder.data.GoogleBookSearchResult;

public class GoogleBookFinder {
    private static final String KEY = "AIzaSyC46Qna_yAqY9ru9qFkWP7-KGLyzLHwP8Y";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String query;

    public GoogleBookFinder(String query) {
        this.query = query;
    }

    public GoogleBookSearchResult find() {
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", getFormattedQuery(query))
                .queryParam("key", KEY)
                .build()
                .toString();

        return restTemplate.getForObject(url, GoogleBookSearchResult.class);
    }

    // Elizabeth+Bennet (with add later " if needed)
    // https://developers.google.com/books/docs/v1/using
    private String getFormattedQuery(String query) {
        return query.replace(" ", "+");
    }
}
