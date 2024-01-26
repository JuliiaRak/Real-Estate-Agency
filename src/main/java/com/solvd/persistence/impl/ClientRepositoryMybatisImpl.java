package com.solvd.persistence.impl;

import com.solvd.domain.Client;
import com.solvd.persistence.Config;
import com.solvd.persistence.ClientRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryMybatisImpl implements ClientRepository {
    @Override
    public void create(Client client) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.create(client);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.deleteById(id);
        }
    }

    @Override
    public void update(Client client) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.update(client);
        }
    }

    @Override
    public Optional<Client> findById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findById(id);
        }
    }

    @Override
    public List<Client> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findAll();
        }
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findByEmail(email);
        }
    }

    @Override
    public Optional<Client> findByPhoneNumber(String phoneNumber) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findByPhoneNumber(phoneNumber);
        }
    }
}
