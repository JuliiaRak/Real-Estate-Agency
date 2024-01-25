package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.exceptions.EmailAlreadyExistException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistException;
import com.solvd.persistence.ClientRepository;
import com.solvd.service.ClientService;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void create(Client client) throws EmailAlreadyExistException, PhoneNumberAlreadyExistException {
        clientCheck(client);
        checkEmailAndPhoneNumber(client);

        clientRepository.create(client);
    }

    @Override
    public void deleteById(long id) {
        if (clientRepository.findById(id).isEmpty()) {
            throw new RuntimeException("The user is not in the database");
        }
        clientRepository.deleteById(id);
    }

    @Override
    public void update(Client client) {
        if (clientRepository.findById(client.getId()).isEmpty()) {
            throw new RuntimeException("The user is not in the database");
        }
        clientCheck(client);
        clientRepository.update(client);
    }

    @Override
    public Client getById(long id) throws EntityNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client", id));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    private void checkEmailAndPhoneNumber(Client client) throws EmailAlreadyExistException, PhoneNumberAlreadyExistException {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("A customer with this email address already exists");
        }

        if (clientRepository.findByPhoneNumber(client.getPhoneNumber()).isPresent()) {
            throw new PhoneNumberAlreadyExistException("A customer with this phone number already exists");
        }
    }

    private void clientCheck(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        if (client.getFirstName() == null) {
            throw new IllegalArgumentException("The firstName cannot be null");
        }
        if (client.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("The firstName cannot be empty");
        }
        if (client.getLastName() == null) {
            throw new IllegalArgumentException("The lastName cannot be null");
        }
        if (client.getLastName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (client.getEmail() == null || client.getEmail().isEmpty() || !client.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-z]+$")) {
            throw new IllegalArgumentException("The email address is incorrect");
        }
        if (client.getPhoneNumber() == null || !client.getPhoneNumber().matches("^\\+?[0-9]{11,15}$")) {
            throw new IllegalArgumentException("The phone number is incorrect");
        }
        if (client.getRegistrationDate() == null) {
            throw new IllegalArgumentException("The registration date cannot be null");
        }
        if (client.getRegistrationDate().after(new Date())) {
            throw new IllegalArgumentException("The registration date cannot be in the future");
        }
    }
}
