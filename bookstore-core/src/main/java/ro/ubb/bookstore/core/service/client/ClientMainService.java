package ro.ubb.bookstore.core.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.bookstore.core.model.Client;
import ro.ubb.bookstore.core.repository.ClientRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientMainService implements ClientService {

    public static final Logger log = LoggerFactory.getLogger(ClientMainService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getClientById(Long id) {
        log.trace("getClientById - method entered: id={}", id);
        return clientRepository.findById(id).get();
    }

    @Override
    public Client addClient(Client client) {
        log.trace("addClient - method entered: client={}", client);
        //clientValidator.validate(client);
        log.trace("addClient - client validated: client={}", client);
        Client c = clientRepository.save(client);
        log.trace("addClient - method finished");
        return c;
    }

    @Override
    public Client removeClient(Long id) {
        log.trace("removeClient - method entered: id={}", id);
        Client c = clientRepository.getOne(id);
        clientRepository.deleteById(id);
        log.trace("removeClient - method finished");
        return c;
    }

    @Override
    public Client updateClient(Client newClient) {
        log.trace("updateClient - method entered: newClient={}", newClient);
        //clientValidator.validate(newClient);
        log.trace("updateClient - newClient validated: newClient={}", newClient);
        clientRepository.findById(newClient.getId()).ifPresent(oldClient -> {
            oldClient.setFirstName(newClient.getFirstName());
            oldClient.setLastName(newClient.getLastName());
            oldClient.setMoneySpent(newClient.getMoneySpent());
            clientRepository.save(oldClient);
            log.debug("updateClient - updated: oldClient={}", oldClient);
        });
        log.trace("updateClient - method finished");
        return clientRepository.getOne(newClient.getId());
    }

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getClients(Set<Long> ids) {
        return clientRepository.findAllById(ids);
    }

    @Override
    public List<Client> filterClientsByName(String name) {
        ArrayList<Client> filteredClients = new ArrayList<>();
        clientRepository.findAll().forEach((client) -> {
            if (client.getFirstName().contains(name) || client.getLastName().contains(name))
            {
                filteredClients.add(client);
            }
        });
        return filteredClients;
    }

    @Override
    public List<Client> topNClientsOnMoneySpent(int n) {
        return clientRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Client::getMoneySpent))
                .collect(Collectors.toList());
    }
}
