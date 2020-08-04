package ro.ubb.bookstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.bookstore.core.model.Book;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.core.service.client.ClientService;
import ro.ubb.bookstore.web.converter.ClientConverter;
import ro.ubb.bookstore.web.dto.ClientDto;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
public class ClientController {

    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    Set<ClientDto> all() {
        log.trace("get all clients controller");
        return clientConverter.convertModelsToDtos(clientService.getAllClients());
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    ClientDto one(@PathVariable Long id) {
        log.trace("get one client controller");
        return clientConverter.convertModelToDto(clientService.getClientById(id));
    }

    @RequestMapping(value = "/clients/ids", method = RequestMethod.PUT)
    Set<ClientDto> ids(@RequestBody HashSet<Long> ids) {
        log.trace("get all clients controller");
        return clientConverter.convertModelsToDtos(clientService.getClients(ids));
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    ClientDto add(@RequestBody ClientDto clientDto) {
        Client client = clientConverter.convertDtoToModel(clientDto);
        return clientConverter.convertModelToDto(clientService.addClient(client));
    }

    @RequestMapping(value = "/client", method = RequestMethod.PUT)
    ClientDto update(@RequestBody ClientDto clientDto) {
        log.trace("update client dto - {}", clientDto);
        Client client = clientConverter.convertDtoToModel(clientDto);
        client = clientService.updateClient(client);
        return clientConverter.convertModelToDto(client);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id) {
        clientService.removeClient(id);
    }
}
