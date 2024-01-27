package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.persistence.ClientRepository;
import com.solvd.persistence.impl.ClientRepositoryMybatisImpl;
import com.solvd.service.ClientService;
import com.solvd.service.PersonService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.date.NotNullDateValidator;
import com.solvd.service.validators.date.PastDateValidator;
import com.solvd.service.validators.object.NotNullObjectValidator;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryMybatisImpl();
    }

    @Override
    public void create(Client client) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        validate(client);
        checkEmailAndPhoneNumber(client);

        clientRepository.create(client);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void update(Client client) throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        if (clientRepository.findById(client.getId()).isEmpty()) {
            throw new EntityNotFoundException("Client", client.getId());
        }
        validate(client);
        checkEmailAndPhoneNumber(client);

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

    @Override
    public Client getByEmail(String email) throws EntityNotFoundException {
        return clientRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Client"));
    }

    @Override
    public Client getByPhoneNumber(String phoneNumber) throws EntityNotFoundException {
        return clientRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new EntityNotFoundException("Client"));
    }

    @Override
    public boolean existsById(long id) {
        return clientRepository.findById(id).isPresent();
    }

    private void checkEmailAndPhoneNumber(Client client) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        if (clientRepository.findByEmail(client.getEmail()).filter(c -> c.getId() != client.getId()).isPresent()) {
            throw new EmailAlreadyExistsException("A customer with this email address already exists");
        }

        if (clientRepository.findByPhoneNumber(client.getPhoneNumber()).filter(c -> c.getId() != client.getId()).isPresent()) {
            throw new PhoneNumberAlreadyExistsException("A customer with this phone number already exists");
        }
    }

    private void validate(Client client) {
        Validator<Object> objectValidator = new NotNullObjectValidator();
        objectValidator.validate("client", client);

        PersonService.validate(client.getFirstName(), client.getFirstName(), client.getEmail(), client.getPhoneNumber());

        Validator<Date> dateValidator = new PastDateValidator(new NotNullDateValidator());
        dateValidator.validate("registration date", client.getRegistrationDate());
    }
}
