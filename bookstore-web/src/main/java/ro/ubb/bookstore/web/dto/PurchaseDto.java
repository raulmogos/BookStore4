package ro.ubb.bookstore.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PurchaseDto {
    private Long bookId;
    private Long clientId;
    private Double pricePaid;
}
