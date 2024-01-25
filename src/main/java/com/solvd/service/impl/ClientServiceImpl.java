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
        clientRepository.deleteById(id);
    }

    @Override
    public void update(Client client) throws EntityNotFoundException {
        if (clientRepository.findById(client.getId()).isEmpty()) {
            throw new EntityNotFoundException("Client");
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

    }
}
