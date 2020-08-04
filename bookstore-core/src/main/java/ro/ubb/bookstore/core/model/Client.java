package ro.ubb.bookstore.core.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "purchases", callSuper = false)
//@Proxy(lazy = false)
public class Client extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    private double moneySpent;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();

    public Set<Book> getBooks() {
        purchases = purchases == null ? new HashSet<>() : purchases;
        return purchases.stream()
                .map(Purchase::getBook)
                .collect(Collectors.toUnmodifiableSet());
    }

    public void addBook(Book book) {
        Purchase purchase = new Purchase();
        purchase.setBook(book);
        purchase.setClient(this);
        purchases.add(purchase);
    }

    public void addBooks(Set<Book> books) {
        books.forEach(this::addBook);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Client client = (Client) o;
//        return Objects.equals(firstName, client.firstName) &&
//               Objects.equals(lastName, client.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstName, lastName);
//    }
//
    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", moneySpent=" + moneySpent +
                ", id=" + super.id +
                '}';
    }
}
