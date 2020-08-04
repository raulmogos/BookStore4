package ro.ubb.bookstore.core.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase")
@IdClass(PurchasePK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
//@Proxy(lazy = false)
public class Purchase implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "price_paid")
    private double pricePaid;

    @Override
    public String toString() {
        return "Purchase{" +
                "book=" + book +
                ", client=" + client +
                '}';
    }
}
