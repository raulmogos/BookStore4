package ro.ubb.bookstore.core.service.purchase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.bookstore.core.model.Purchase;
import ro.ubb.bookstore.core.repository.PurchaseRepository;

import java.util.List;

@Service
public class PurchaseMainService implements PurchaseService {

    public static final Logger log = LoggerFactory.getLogger(PurchaseMainService.class);

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase getPurchaseById(Long id) {
        log.trace("getPurchaseById - method entered: id={}", id);
        return purchaseRepository.getOne(id);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        log.trace("addPurchase - method entered: purchase={}", purchase);
        //purchaseValidator.validate(purchase);
        log.trace("addPurchase - purchase validated: purchase={}", purchase);
        purchaseRepository.save(purchase);
        log.trace("addPurchase - method finished");
    }

    @Override
    public void removePurchase(Long id) {
        log.trace("removePurchase - method entered: id={}", id);
        purchaseRepository.deleteById(id);
        log.trace("removePurchase - method finished");
    }

    @Override
    public void updatePurchase(Purchase newPurchase) {
        log.trace("updatePurchase - method entered: newPurchase={}", newPurchase);
        //purchaseValidator.validate(newPurchase);
        log.trace("updatePurchase - newPurchase validated: newPurchase={}", newPurchase);
        purchaseRepository.findById(newPurchase.getId()).ifPresent(oldPurchase -> {
            oldPurchase.setBookId(newPurchase.getBookId());
            oldPurchase.setClientId(newPurchase.getClientId());
            purchaseRepository.save(oldPurchase);
            log.debug("updatePurchase - updated: oldPurchase={}", oldPurchase);
        });
        log.trace("updatePurchase - method finished");
    }

    @Override
    public List<Purchase> getAllPurchases() {
        log.trace("getAllPurchases - method entered");
        return purchaseRepository.findAll();
    }
}
