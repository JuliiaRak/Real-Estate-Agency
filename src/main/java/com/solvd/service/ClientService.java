package com.solvd.service;

import com.solvd.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void create(Client client);
    void deleteById(long id);
    Optional<Client> getById(long id);
    List<Client> getAll();
}
