package com.solvd.service;

import com.solvd.domain.Client;

import java.util.List;

public interface ClientService {
    void create(Client client);
    void deleteById(long id);
    Client findById(long id);
    List<Client> getAll();
}
