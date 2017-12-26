package xyz.libris.api.unit;

import org.junit.Test;
import xyz.libris.api.google.book.finder.GoogleBookFinder;
import xyz.libris.api.google.book.finder.data.GoogleBookSearchResult;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleBookFinderTests {

    @Test
    public void testFindByInTitleAndAuthor() {
        GoogleBookFinder googleBookFinder = new GoogleBookFinder("red",
                "brown",
                null);
        GoogleBookSearchResult result = googleBookFinder.find();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }

    @Test
    public void testFindByInAuthor() {
        GoogleBookFinder googleBookFinder = new GoogleBookFinder(null,
                "Pierce Brown",
                "");
        GoogleBookSearchResult result = googleBookFinder.find();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }

    @Test
    public void testFindByIsbn() {
        GoogleBookFinder googleBookFinder = new GoogleBookFinder("",
                "",
                "9783453269576");
        GoogleBookSearchResult result = googleBookFinder.find();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }
}
