package ro.ubb.bookstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.core.model.Purchase;
import ro.ubb.bookstore.core.service.book.BookService;
import ro.ubb.bookstore.core.service.client.ClientService;
import ro.ubb.bookstore.core.service.purchase.PurchaseService;
import ro.ubb.bookstore.web.converter.BookConverter;
import ro.ubb.bookstore.web.converter.ClientConverter;
import ro.ubb.bookstore.web.converter.PurchaseConverter;
import ro.ubb.bookstore.web.dto.BookDto;
import ro.ubb.bookstore.web.dto.ClientDto;
import ro.ubb.bookstore.web.dto.PurchaseDto;

import java.util.Set;

@CrossOrigin
@RestController
public class PurchaseController {

    public static final Logger log = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PurchaseConverter purchaseConverter;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
    Set<PurchaseDto> all() {
        log.trace("get all purchases controller");
        return purchaseConverter.convertModelsToDtos(purchaseService.getAllPurchases());
    }

    @RequestMapping(value = "/purchases/books-to-buy", method = RequestMethod.PUT)
    Set<BookDto> booksForClient(@RequestBody ClientDto clientDto) {
        Client client = clientConverter.convertDtoToModel(clientDto);
        return bookConverter.convertModelsToDtos(purchaseService.getBooksToBuyFormThisClient(client));
    };

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    void add(@RequestBody PurchaseDto purchaseDto) {
        log.trace("add purchase controller - purchaseDto={}", purchaseDto);
        Book book = bookService.getBookById(purchaseDto.getBookId());
        Client client = clientService.getClientById(purchaseDto.getClientId());
        purchaseService.addPurchase(client, book);
    }
}
