package xyz.libris.api.unit;

import org.junit.Test;
import xyz.libris.api.google.book.finder.GoogleBookFinder;
import xyz.libris.api.google.book.finder.data.GoogleBookSearchResult;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleBookFinderTests {

    @Test
    public void testFind() {
        GoogleBookFinder googleBookFinder = new GoogleBookFinder("9781136183416");
        GoogleBookSearchResult result = googleBookFinder.find();

        assertThat(result.getTotalItems()).isNotNull();
        assertThat(result.getItems()).isNotEmpty();
    }
}
