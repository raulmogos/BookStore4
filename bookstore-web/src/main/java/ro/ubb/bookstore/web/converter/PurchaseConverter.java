package ro.ubb.bookstore.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.core.model.Purchase;
import ro.ubb.bookstore.core.service.book.BookService;
import ro.ubb.bookstore.core.service.client.ClientService;
import ro.ubb.bookstore.web.dto.PurchaseDto;

@Component
public class PurchaseConverter extends AbstractConverter<Purchase, PurchaseDto> {

    @Autowired
    private BookService bookService;

    @Autowired
    private ClientService clientService;

    @Override
    public Purchase convertDtoToModel(PurchaseDto purchaseDto) {
        Book book = bookService.getBookById(purchaseDto.getBookId());
        Client client = clientService.getClientById(purchaseDto.getClientId());
        Double pricePaid = purchaseDto.getPricePaid();
        return Purchase.builder()
                .book(book)
                .client(client)
                .pricePaid(pricePaid)
                .build();
    }

    @Override
    public PurchaseDto convertModelToDto(Purchase purchase) {
        return PurchaseDto.builder()
                .bookId(purchase.getBook().getId())
                .clientId(purchase.getClient().getId())
                .pricePaid(purchase.getPricePaid())
                .build();
    }
}
