package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.persistence.repositories.ClientRepository;
import com.solvd.service.ClientService;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client client) {
        clientRepository.create(client);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }
}
