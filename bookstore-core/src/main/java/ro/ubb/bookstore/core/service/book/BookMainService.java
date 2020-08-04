package ro.ubb.bookstore.core.service.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookMainService implements BookService {

    public static final Logger log = LoggerFactory.getLogger(BookMainService.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        log.trace("getBookById - method entered: id={}", id);
        return bookRepository.getOne(id);
    }

    @Override
    public Book addBook(Book book) {
        log.trace("addBook - method entered: book={}", book);
        //bookValidator.validate(book);
        log.trace("addBook - book validated: book={}", book);
        Book b = bookRepository.save(book);
        log.trace("addBook - method finished");
        return b;
    }

    @Override
    public Book removeBook(Long id) {
        log.trace("removeBook - method entered: id={}", id);
        Book book = bookRepository.getOne(id);
        bookRepository.deleteById(id);
        log.trace("removeBook - method finished");
        return book;
    }

    @Override
    public Book updateBook(Book newBook) {
        log.trace("updateBook - method entered: newBook={}", newBook);
        //bookValidator.validate(newBook);
        log.trace("updateBook - newBook validated: newBook={}", newBook);
        bookRepository.findById(newBook.getId()).ifPresent(oldBook -> {
            oldBook.setTitle(newBook.getTitle());
            oldBook.setAuthor(newBook.getAuthor());
            oldBook.setPrice(newBook.getPrice());
            bookRepository.save(oldBook);
            log.debug("updateBook - updated: oldBook={}", oldBook);
        });
        log.trace("updateBook - method finished");
        return bookRepository.getOne(newBook.getId());
    }

    @Override
    public List<Book> getAllBooks() {
        log.trace("getAllBooks - method entered");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooks(Set<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    @Override
    public List<Book> filterBookAuthor(String author) {
        ArrayList<Book> filteredBooks = new ArrayList<>();
        bookRepository.findAll().forEach((book) -> {
            if (book.getAuthor().equals(author)) {
                filteredBooks.add(book);
            }
        });
        return filteredBooks;
    }

    @Override
    public List<Book> filterBookPrice(double min, double max) {
        ArrayList<Book> filteredBooks = new ArrayList<>();
        bookRepository.findAll().forEach((book) -> {
            if (book.getPrice() >= min && book.getPrice() <= max) {
                filteredBooks.add(book);
            }
        });
        return filteredBooks;
    }
}
