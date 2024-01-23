package com.solvd.persistence.repositories;

import com.solvd.domain.Client;

public interface ClientRepository {
    void create(Client client);
    void deleteById(long id);
}
