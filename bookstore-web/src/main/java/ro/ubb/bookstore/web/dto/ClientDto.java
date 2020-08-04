package ro.ubb.bookstore.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto extends BaseDto {
    private String firstName;
    private String lastName;
    private double moneySpent;
    private Set<Long> booksIds;

    @Override
    public String toString() {
        return "ClientDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", moneySpent=" + moneySpent +
                '}';
    }
}
