package ro.ubb.bookstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.web.dto.BookDto;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {

    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = new Book(
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPrice()
        );
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto bookDto = new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getPrice()
        );
        bookDto.setId(book.getId());
        return bookDto;
    }

}
