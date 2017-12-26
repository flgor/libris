package xyz.libris.api.unit;

import org.junit.Test;
import xyz.libris.api.book.google.client.GoogleBookSearcher;
import xyz.libris.api.book.google.client.data.GoogleBookSearchResult;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleBookSearcherTests {

    @Test
    public void testFindByInTitleAndAuthor() {
        GoogleBookSearcher googleBookSearcher = new GoogleBookSearcher("red",
                "brown",
                null);
        GoogleBookSearchResult result = googleBookSearcher.search();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }

    @Test
    public void testFindByInAuthor() {
        GoogleBookSearcher googleBookSearcher = new GoogleBookSearcher("",
                "brown",
                "");
        GoogleBookSearchResult result = googleBookSearcher.search();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }

    @Test
    public void testFindByIsbn() {
        GoogleBookSearcher googleBookSearcher = new GoogleBookSearcher("",
                "",
                "9783453269576");
        GoogleBookSearchResult result = googleBookSearcher.search();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }
}
