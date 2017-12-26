package xyz.libris.api.book.google.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.libris.api.book.google.client.data.GoogleBookSearchResult;

public class GoogleBookSearcher {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String KEY = "AIzaSyC46Qna_yAqY9ru9qFkWP7-KGLyzLHwP8Y";
    private final RestTemplate restTemplate = new RestTemplate();

    private final String inTitle;
    private final String inAuthor;
    private final String isbn;

    public GoogleBookSearcher(String inTitle,
                              String inAuthor,
                              String isbn) {
        this.inTitle = inTitle;
        this.inAuthor = inAuthor;
        this.isbn = isbn;
    }

    public GoogleBookSearchResult search() {
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", getFormattedQuery())
                .queryParam("key", KEY)
                .build()
                .toString();
        try {

            return restTemplate.getForObject(url, GoogleBookSearchResult.class);
        } catch (HttpClientErrorException clientException) {
            log.warn("error during google books search: [" + url + "].", clientException);
            return new GoogleBookSearchResult();
        }
    }

    // https://www.googleapis.com/books/v1/volumes?q=intitle:red+inauthor:brown&key=akey
    // https://developers.google.com/books/docs/v1/using
    private String getFormattedQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        boolean plusNeeded = false;

        if (StringUtils.isNotEmpty(inTitle) && StringUtils.isNotEmpty(inTitle.trim())) {
            plusNeeded = true;
            queryBuilder.append("intitle:")
                    .append(inTitle.trim());
        }

        if (StringUtils.isNotEmpty(inAuthor) && StringUtils.isNotEmpty(inAuthor.trim())) {
            if (plusNeeded) {
                queryBuilder.append("+");
            }
            plusNeeded = true;

            queryBuilder.append("inauthor:")
                    .append(inAuthor.trim());
        }

        if (StringUtils.isNotEmpty(isbn) && StringUtils.isNotEmpty(isbn.trim())) {
            if (plusNeeded) {
                queryBuilder.append("+");
            }

            queryBuilder.append("isbn:")
                    .append(isbn.trim());
        }

        String query = queryBuilder.toString();
        return query.replace(" ", "+");
    }
}
