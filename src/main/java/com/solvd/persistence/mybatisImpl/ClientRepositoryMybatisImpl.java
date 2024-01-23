package com.solvd.persistence.mybatisImpl;

import com.solvd.domain.Client;
import com.solvd.persistence.Config;
import com.solvd.persistence.repositories.ClientRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
    public Client findById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findById(id);
        }
    }

    @Override
    public List<Client> getAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getAll();
        }
    }
}
