package xyz.libris.api.book.google;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.libris.api.book.BookService;
import xyz.libris.api.book.CreateBookDto;
import xyz.libris.api.book.google.client.GoogleBookFinder;
import xyz.libris.api.book.google.client.GoogleBookSearcher;
import xyz.libris.api.book.google.client.data.GoogleBook;
import xyz.libris.api.book.google.client.data.GoogleBookSearchResult;
import xyz.libris.api.book.google.client.data.GoogleVolumeInfo;
import xyz.libris.api.book.google.dto.GoogleAuthorsTransformers;
import xyz.libris.api.book.google.dto.GoogleBookSearchResultDto;
import xyz.libris.api.book.google.dto.GoogleBookSearchResultDtoConverter;
import xyz.libris.api.book.google.dto.GoogleIndustryIdentifierDeterminer;
import xyz.libris.api.security.SecurityService;
import xyz.libris.api.user.User;

@RestController
public class GoogleBookController {

    private final BookService bookService;
    private final SecurityService securityService;

    public GoogleBookController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    @GetMapping("/api/v1/book/google/search/public")
    public GoogleBookSearchResultDto search(@RequestParam(value = "intitle", required = false) String inTitle,
                                            @RequestParam(value = "inauthor", required = false) String inAuthor,
                                            @RequestParam(value = "isbn", required = false) String isbn) {
        GoogleBookSearchResult result = new GoogleBookSearcher(inTitle, inAuthor, isbn).search();
        return new GoogleBookSearchResultDtoConverter(result).convert();
    }

    @PostMapping("/api/v1/book/google/{googleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void search(@PathVariable("googleId") String googleId) {
        User currentUser = securityService.getCurrentUser();

        GoogleBook googleBook = new GoogleBookFinder(googleId).find();
        GoogleVolumeInfo volumeInfo = googleBook.getVolumeInfo();

        CreateBookDto dto = new CreateBookDto();
        dto.setTitle(volumeInfo.getTitle());

        dto.setAuthors(new GoogleAuthorsTransformers(volumeInfo.getAuthors())
                .transform());

        dto.setDescription(volumeInfo.getDescription());
        dto.setIsbn13(new GoogleIndustryIdentifierDeterminer(volumeInfo.getIndustryIdentifiers())
                .determinIsbn13());
        dto.setPublishedDate(volumeInfo.getPublishedDate());
        dto.setPublisher(volumeInfo.getPublisher());

        bookService.create(dto, currentUser, currentUser);
    }
}
