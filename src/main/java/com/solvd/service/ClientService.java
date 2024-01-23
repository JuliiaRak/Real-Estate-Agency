package com.solvd.service;

import com.solvd.domain.Client;

public interface ClientService {
    void create(Client client);
    void deleteById(long id);
}
