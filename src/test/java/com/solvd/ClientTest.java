package com.solvd;

import com.solvd.domain.Client;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.persistence.ClientRepository;
import com.solvd.persistence.impl.ClientRepositoryMybatisImpl;
import com.solvd.service.ClientService;
import com.solvd.service.impl.ClientServiceImpl;

import java.util.Date;

public class ClientTest {
    public static void main(String[] args) throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
        Client.Builder builder = new Client.Builder();
        try {
            builder.setFirstName("Denys");
            builder.setLastName("Kulikov");
            builder.setEmail("dkulikov@gmail.com");
            builder.setPhoneNumber("+380991234567");
            builder.setRegistrationDate(new Date());
        } catch (IllegalArgumentException e) {
            System.out.println("An invalid object was created: " + e.getMessage());
        }

        Client client = builder.build();
        System.out.println(client);
        Client client2 = new Client();

        client2.setFirstName("Ivan");
        client2.setLastName("Kulikov");
        client2.setEmail("ikulikov@gmail.com");
        client2.setPhoneNumber("+222-22-222-22-22");
        client2.setRegistrationDate(new Date());

        ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        try {
            clientService.create(client);
            clientService.create(client2);
        } catch (EmailAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (PhoneNumberAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (FieldValidationException e) {
            throw new RuntimeException(e);
        }

        client.setEmail("dkulikov@ukr.net");
        client.setPhoneNumber("+333-33-111-11-11");
        clientService.update(client);
        System.out.println(clientService.getById(client.getId()));
        System.out.println(clientService.getAll());


        clientService.deleteById(client.getId());
        clientService.deleteById(client2.getId());
    }
}
