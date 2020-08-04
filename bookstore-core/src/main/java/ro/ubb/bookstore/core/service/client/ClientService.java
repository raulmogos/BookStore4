package ro.ubb.bookstore.core.service.client;

import ro.ubb.bookstore.core.model.Client;

import java.util.List;
import java.util.Set;

public interface ClientService {

    String NAME = "ClientService";

    Client getClientById(Long id);

    Client addClient(Client client);

    Client removeClient(Long id);

    Client updateClient(Client client);

    List<Client> getAllClients();

    List<Client> getClients(Set<Long> ids);

    List<Client> filterClientsByName(String name);

    List<Client> topNClientsOnMoneySpent(int n);
}