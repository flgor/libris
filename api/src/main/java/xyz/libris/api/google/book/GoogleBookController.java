package xyz.libris.api.google.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.libris.api.google.book.dto.GoogleBookSearchResultDto;
import xyz.libris.api.google.book.dto.GoogleBookSearchResultDtoConverter;
import xyz.libris.api.google.book.finder.GoogleBookFinder;
import xyz.libris.api.google.book.finder.data.GoogleBookSearchResult;

@RestController
public class GoogleBookController {


    @GetMapping("/api/v1/book/google/search/public")
    public GoogleBookSearchResultDto search(@RequestParam("q") String query) {
        GoogleBookSearchResult result = new GoogleBookFinder(query).find();
        return new GoogleBookSearchResultDtoConverter(result).convert();
    }
}
