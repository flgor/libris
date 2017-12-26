package xyz.libris.api.google.book.finder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.libris.api.google.book.finder.data.GoogleBookSearchResult;

public class GoogleBookFinder {
    private static final String KEY = "AIzaSyC46Qna_yAqY9ru9qFkWP7-KGLyzLHwP8Y";
    private final RestTemplate restTemplate = new RestTemplate();

    private final String inTitle;
    private final String inAuthor;
    private final String isbn;

    public GoogleBookFinder(String inTitle,
                            String inAuthor,
                            String isbn) {
        this.inTitle = inTitle;
        this.inAuthor = inAuthor;
        this.isbn = isbn;
    }

    public GoogleBookSearchResult find() {
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", getFormattedQuery())
                .queryParam("key", KEY)
                .build()
                .toString();

        return restTemplate.getForObject(url, GoogleBookSearchResult.class);
    }

    // https://www.googleapis.com/books/v1/volumes?q=intitle:red+inauthor:brown&key=akey
    // https://developers.google.com/books/docs/v1/using
    private String getFormattedQuery() {
        StringBuilder queryBuilder = new StringBuilder();

        if (StringUtils.isNotEmpty(inTitle) && StringUtils.isNotEmpty(inTitle.trim())) {
            queryBuilder.append("intitle:")
                    .append(inTitle.trim());
        }

        if (StringUtils.isNotEmpty(inAuthor) && StringUtils.isNotEmpty(inAuthor.trim())) {
            queryBuilder.append("+");
            queryBuilder.append("inauthor:")
                    .append(inAuthor.trim());
        }

        if (StringUtils.isNotEmpty(isbn) && StringUtils.isNotEmpty(isbn.trim())) {
            queryBuilder.append("+");
            queryBuilder.append("isbn:")
                    .append(isbn.trim());
        }

        String query = queryBuilder.toString();
        return query.replace(" ", "+");
    }
}
