package xyz.libris.api.book;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.libris.api.user.User;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(CreateBookDto createBookDto, User owner, User currentUser) {
        Book book = new Book();

        book.setTitle(createBookDto.getTitle());
        book.setAuthors(createBookDto.getAuthors());
        book.setDescription(createBookDto.getDescription());
        book.setPublisher(createBookDto.getPublisher());
        book.setPublishedDate(createBookDto.getPublishedDate());
        book.setIsbn13(createBookDto.getIsbn13());
        book.setUniqueId(RandomStringUtils.randomAlphanumeric(15));

        book.setOwnerId(owner.getId());

        book.setCreatedById(currentUser.getId());
        book.setCreatedDate(DateTime.now().toDate());

        return bookRepository.save(book);
    }

    public List<Book> getAll(User user) {
        log.info("Get all books for user: [" + user.getEmail() + "].");
        return bookRepository.findAllByOwnerId(user.getId());
    }
}
