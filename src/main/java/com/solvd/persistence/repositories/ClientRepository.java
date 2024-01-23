package com.solvd.persistence.repositories;

import com.solvd.domain.Client;

import java.util.List;

public interface ClientRepository {
    void create(Client client);
    void deleteById(long id);
    Client findById(long id);
    List<Client> getAll();
}
