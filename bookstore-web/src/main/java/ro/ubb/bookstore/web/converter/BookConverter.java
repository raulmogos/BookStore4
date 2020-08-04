package ro.ubb.bookstore.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.web.dto.BookDto;

import java.util.stream.Collectors;

@Component
public class BookConverter extends AbstractConverterBaseEntity<Book, BookDto> {

    public static final Logger log = LoggerFactory.getLogger(BookConverter.class);

    @Override
    public Book  convertDtoToModel(BookDto bookDto) {
        log.trace("convertDtoToModel enter- bookDto={}", bookDto);
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .build();
        if (bookDto.getId() != null) {
            book.setId(bookDto.getId());
        }
        log.trace("convertDtoToModel finish- book={}", book);
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        log.trace("convertModelToDto enter- book={}", book);
        BookDto bookDto = BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .clientsIds(book.getClients().stream().map(Client::getId).collect(Collectors.toSet()))
                .build();
        bookDto.setId(book.getId());
        log.trace("convertModelToDto finish- bookDto={}", bookDto);
        return bookDto;
    }

}
