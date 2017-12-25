package xyz.libris.api.unit;

import org.junit.Test;
import xyz.libris.api.google.books.data.GoogleBook;
import xyz.libris.api.google.books.GoogleBookFinder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleBookFinderTests {

    @Test
    public void testFind() {
        GoogleBookFinder googleBookFinder = new GoogleBookFinder("9781136183416");
        List<GoogleBook> googleBooks = googleBookFinder.find();

        assertThat(googleBooks).isNotEmpty();
    }
}
