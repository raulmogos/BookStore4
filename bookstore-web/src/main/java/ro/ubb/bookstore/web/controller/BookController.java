package ro.ubb.bookstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.service.book.BookService;
import ro.ubb.bookstore.web.converter.BookConverter;
import ro.ubb.bookstore.web.dto.BookDto;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
public class BookController {

    public static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    Set<BookDto> all() {
        log.trace("get all books controller");
        return bookConverter.convertModelsToDtos(bookService.getAllBooks());
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    BookDto one(@PathVariable Long id) {
        log.trace("get one book controller");
        return bookConverter.convertModelToDto(bookService.getBookById(id));
    }

    @RequestMapping(value = "/books/ids", method = RequestMethod.PUT)
    Set<BookDto> ids(@RequestBody HashSet<Long> ids) {
        log.trace("get all books from ids controller");
        return bookConverter.convertModelsToDtos(bookService.getBooks(ids));
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    BookDto add(@RequestBody BookDto bookDto) {
        Book book = bookConverter.convertDtoToModel(bookDto);
        return bookConverter.convertModelToDto(bookService.addBook(book));
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    BookDto update(@RequestBody BookDto bookDto) {
        log.trace("update book dto - {}", bookDto);
        Book book = bookConverter.convertDtoToModel(bookDto);
        book = bookService.updateBook(book);
        return bookConverter.convertModelToDto(book);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id) {
        bookService.removeBook(id);
    }
}
