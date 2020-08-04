package ro.ubb.bookstore.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.web.dto.ClientDto;

import java.util.stream.Collectors;

@Component
public class ClientConverter extends AbstractConverterBaseEntity<Client, ClientDto> {

    public static final Logger log = LoggerFactory.getLogger(ClientConverter.class);

    @Override
    public Client convertDtoToModel(ClientDto clientDto) {
        log.trace("convertDtoToModel enter- clientDto={}", clientDto);
        Client client = Client.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .moneySpent(clientDto.getMoneySpent())
                .build();
        if (clientDto.getId() != null) {
            client.setId(clientDto.getId());
        }
        log.trace("convertDtoToModel finish- client={}", client);
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        log.trace("convertModelToDto enter- client={}", client);
        ClientDto clientDto = ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .moneySpent(client.getMoneySpent())
                .booksIds(client.getBooks().stream().map(Book::getId).collect(Collectors.toSet()))
                .build();
        clientDto.setId(client.getId());
        log.trace("convertModelToDto finish- clientDto={}", clientDto);
        return clientDto;
    }
}
