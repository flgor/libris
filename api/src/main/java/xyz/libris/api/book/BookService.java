package xyz.libris.api.book;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.libris.api.user.User;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(String author, String title, Integer year, User owner, User currentUser) {
        Book book = new Book();

        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        book.setUniqueId(RandomStringUtils.randomAlphanumeric(15));

        book.setOwnerId(owner.getId());

        book.setCreatedById(currentUser.getId());
        book.setCreatedDate(DateTime.now().toDate());

        return bookRepository.save(book);
    }

    public List<Book> getAll(User user) {
        return bookRepository.findAllByOwnerId(user.getId());
    }
}
