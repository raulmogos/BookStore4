package ro.ubb.bookstore.core.service.book;


import ro.ubb.bookstore.core.model.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    String NAME = "BookService";

    Book getBookById(Long id);

    Book addBook(Book book);

    Book removeBook(Long id);

    Book updateBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooks(Set<Long> ids);

    List<Book> filterBookAuthor(String author);

    List<Book> filterBookPrice(double min, double max);
}
