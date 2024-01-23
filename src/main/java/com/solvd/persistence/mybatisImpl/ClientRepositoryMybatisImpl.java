package com.solvd.persistence.mybatisImpl;

import com.solvd.domain.Client;
import com.solvd.persistence.Config;
import com.solvd.persistence.repositories.ClientRepository;
import org.apache.ibatis.session.SqlSession;

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
}
