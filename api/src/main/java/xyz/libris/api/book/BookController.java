package xyz.libris.api.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.libris.api.security.SecurityService;
import xyz.libris.api.user.User;

import java.util.List;

@RestController
public class BookController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SecurityService securityService;
    private final BookService bookService;

    public BookController(SecurityService securityService, BookService bookService) {
        this.securityService = securityService;
        this.bookService = bookService;
    }

    @PostMapping("/api/v1/book")
    public Book createUser(@RequestBody CreateBookDto createBookDto) {

        User currentUser = securityService.getCurrentUser();
        Book book = bookService.create(createBookDto, currentUser, currentUser);

        log.info("User with email: [" + currentUser.getEmail() + "] created book: [" + createBookDto.getTitle() + "|" +
                createBookDto.getAuthors() + "|" +
                createBookDto.getPublishedDate() + "].");

        return book;
    }

    @GetMapping("/api/v1/book")
    public List<Book> getAllBooks() {
        User currentUser = securityService.getCurrentUser();
        return bookService.getAll(currentUser);
    }
}
