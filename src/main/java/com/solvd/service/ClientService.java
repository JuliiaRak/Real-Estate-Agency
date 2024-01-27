package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;

import java.util.List;

public interface ClientService {
    void create(Client client) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException;

    void deleteById(long id);

    void update(Client client) throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException;

    Client getById(long id) throws EntityNotFoundException;

    List<Client> getAll();

    Client getByEmail(String email) throws EntityNotFoundException;

    Client getByPhoneNumber(String phoneNumber) throws EntityNotFoundException;

    boolean existsById(long id);
}
