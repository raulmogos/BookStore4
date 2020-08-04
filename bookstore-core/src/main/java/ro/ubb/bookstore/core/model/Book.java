package ro.ubb.bookstore.core.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "purchases", callSuper = false)
@ToString(exclude = "purchases")
//@Proxy(lazy = false)
public class Book extends BaseEntity<Long> {

    private String title;
    private String author;
    private double price;
    @OneToMany(mappedBy = "book", cascade =  CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();

    public Set<Client> getClients() {
        purchases = purchases == null ? new HashSet<>() : purchases;
        return purchases.stream()
                .map(Purchase::getClient)
                .collect(Collectors.toUnmodifiableSet());
    }

    public void addClient(Client client) {
        Purchase purchase = new Purchase();
        purchase.setClient(client);
        purchase.setBook(this);
        purchases.add(purchase);
    }

    public void addClients(Set<Client> clients) {
        clients.forEach(this::addClient);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Book book = (Book) o;
//        return Objects.equals(title, book.title) &&
//               Objects.equals(author, book.author);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(title, author);
//    }
//
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", id=" + super.id +
                '}';
    }
}
