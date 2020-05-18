package ro.ubb.bookstore.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Purchase extends BaseEntity<Long> {

    //private Book book;
    //private Client client;
    private Long bookId;
    private Long clientId;

}
