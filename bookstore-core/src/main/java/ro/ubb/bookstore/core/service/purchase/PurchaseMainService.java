package ro.ubb.bookstore.core.service.purchase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.core.model.Purchase;
import ro.ubb.bookstore.core.repository.BookRepository;
import ro.ubb.bookstore.core.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseMainService implements PurchaseService {

    public static final Logger log = LoggerFactory.getLogger(PurchaseMainService.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Purchase getPurchaseById(Long id) {
        log.trace("getPurchaseById - method entered: id={}", id);
        return new Purchase();
    }

    @Override
    public void addPurchase(Client client, Book book) {
        log.trace("addPurchase - method entered: client={}, book={}", client, book);
        //purchaseValidator.validate(purchase);
        log.trace("addPurchase - purchase validated: client={}, book={}", client, book);
        Book b = bookRepository.getOne(book.getId());
        clientRepository.findById(client.getId()).ifPresent(oldClient -> {
            oldClient.addBook(b);
//            clientRepository.save(oldClient);
        });
//        client.addBook(book);
//        clientRepository.saveAndFlush(client);
//        bookRepository.save(book);
        log.trace("addPurchase - method finished");
    }

    @Override
    public void removePurchase(Long id) {
        log.trace("removePurchase - method entered: id={}", id);

        log.trace("removePurchase - method finished");
    }

    @Override
    public void updatePurchase(Purchase newPurchase) {
        log.trace("updatePurchase - method entered: newPurchase={}", newPurchase);
        //purchaseValidator.validate(newPurchase);
        log.trace("updatePurchase - newPurchase validated: newPurchase={}", newPurchase);
        // todo
//        purchaseRepository.findById(newPurchase.getId()).ifPresent(oldPurchase -> {
//            oldPurchase.setBookId(newPurchase.getBookId());
//            oldPurchase.setClientId(newPurchase.getClientId());
//            purchaseRepository.save(oldPurchase);
//            log.debug("updatePurchase - updated: oldPurchase={}", oldPurchase);
//        });
        log.trace("updatePurchase - method finished");
    }

    @Override
    public List<Purchase> getAllPurchases() {
        log.trace("getAllPurchases - method entered");
        ArrayList<Purchase> all = new ArrayList<>();
        bookRepository.findAll().forEach(book -> all.addAll(book.getPurchases()));
        return all;
    }

    @Override
    public List<Book> getBooksToBuyFormThisClient(Client client) {
        log.trace("getBooksToBuyFormThisClient - method entered - client={}", client);
        return bookRepository.findAll().stream()
                .filter(book -> !book.getClients().contains(client))
                .collect(Collectors.toList());
    }
}
