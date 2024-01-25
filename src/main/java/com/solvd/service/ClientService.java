package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.exceptions.EmailAlreadyExistException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void create(Client client) throws EmailAlreadyExistException, PhoneNumberAlreadyExistException;
    void deleteById(long id);
    void update(Client client) throws EntityNotFoundException;
    Client getById(long id) throws EntityNotFoundException;
    List<Client> getAll();
}
