package com.solvd;

import com.solvd.domain.Client;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.service.ClientService;
import com.solvd.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class ClientTest {

    private ClientService clientService = new ClientServiceImpl();

    @Test
    public void createClientTest() throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, EntityNotFoundException, FieldValidationException {
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

        clientService.create(client);
        Client createdClient = clientService.getById(client.getId());

        Assertions.assertNotNull(createdClient);
        Assertions.assertEquals(client, createdClient);
        clientService.deleteById(createdClient.getId());
    }

    @Test
    public void getClientByIdTest() throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
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
        clientService.create(client);
        Client retrievedClient = clientService.getById(client.getId());

        Assertions.assertEquals(client, retrievedClient);
        clientService.deleteById(retrievedClient.getId());
    }

    @Test
    public void getAllClientsTest() throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
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
        Client client1 = builder.build();

        try {
            builder.setFirstName("Ivan");
            builder.setLastName("Kulikov");
            builder.setEmail("ikulikov@gmail.com");
            builder.setPhoneNumber("+380922234567");
            builder.setRegistrationDate(new Date());
        } catch (IllegalArgumentException e) {
            System.out.println("An invalid object was created: " + e.getMessage());
        }
        Client client2 = builder.build();

        clientService.create(client1);
        clientService.create(client2);
        List<Client> allClients = clientService.getAll();

        Assertions.assertTrue(allClients.contains(client1));
        Assertions.assertTrue(allClients.contains(client2));
        clientService.deleteById(client1.getId());
        clientService.deleteById(client2.getId());
    }

    @Test
    public void updateClientTest() throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
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
        clientService.create(client);
        client.setEmail("dkulikov@ukr.net");
        client.setPhoneNumber("+380991234533");
        clientService.update(client);
        Client updatedClient = clientService.getById(client.getId());

        Assertions.assertEquals("dkulikov@ukr.net", updatedClient.getEmail());
        Assertions.assertEquals("+380991234533", updatedClient.getPhoneNumber());
        clientService.deleteById(client.getId());
    }

    @Test
    public void deleteClientTest() throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
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
        clientService.create(client);
        clientService.deleteById(client.getId());
        Client retrievedClient = null;
        try {
            retrievedClient = clientService.getById(client.getId());
        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }

        Assertions.assertNull(retrievedClient);
    }
}
