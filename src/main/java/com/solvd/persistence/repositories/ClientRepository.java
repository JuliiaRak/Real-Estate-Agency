package com.solvd.persistence.repositories;

import com.solvd.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void create(Client client);
    void deleteById(long id);
    void update(Client client);
    Optional<Client> findById(long id);
    List<Client> findAll();
}
