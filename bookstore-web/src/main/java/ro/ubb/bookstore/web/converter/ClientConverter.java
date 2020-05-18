package ro.ubb.bookstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = new Client(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMoneySpent()
        );
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = new ClientDto(
                client.getFirstName(),
                client.getLastName(),
                client.getMoneySpent()
        );
        clientDto.setId(client.getId());
        return clientDto;
    }

}
