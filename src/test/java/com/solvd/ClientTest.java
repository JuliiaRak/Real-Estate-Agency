package com.solvd;

import com.solvd.domain.Client;
import com.solvd.persistence.impl.ClientRepositoryMybatisImpl;
import com.solvd.persistence.ClientRepository;
import com.solvd.service.ClientService;
import com.solvd.service.impl.ClientServiceImpl;

import java.util.Date;

public class ClientTest {
    public static void main(String[] args) {
        Client client = new Client();
        Client client2 = new Client();
        client.setFirstName("Denys");
        client.setLastName("Kulikov");
        client.setEmail("dkulikov@gmail.com");
        client.setPhoneNumber("+111-11-111-11-11");
        client.setRegistrationDate(new Date());

        client2.setFirstName("Ivan");
        client2.setLastName("Kulikov");
        client2.setEmail("ikulikov@gmail.com");
        client2.setPhoneNumber("+222-22-222-22-22");
        client2.setRegistrationDate(new Date());

        ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        clientService.create(client);
        clientService.create(client2);

        client.setEmail("dkulikov@ukr.net");
        client.setPhoneNumber("+333-33-111-11-11");
        clientService.update(client);
        System.out.println(clientService.getById(client.getId()));
        System.out.println(clientService.getAll());

        clientService.deleteById(client.getId());
        clientService.deleteById(client2.getId());
    }
}
