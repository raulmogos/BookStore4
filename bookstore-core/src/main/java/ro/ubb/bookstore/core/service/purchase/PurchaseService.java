package ro.ubb.bookstore.core.service.purchase;

import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.core.model.Purchase;

import java.util.List;

public interface PurchaseService {

    String NAME = "PurchaseService";

    Purchase getPurchaseById(Long id);

    void addPurchase(Client client, Book book);

    void removePurchase(Long id);

    void updatePurchase(Purchase newPurchase);

    List<Purchase> getAllPurchases();

    List<Book> getBooksToBuyFormThisClient(Client client);
}
