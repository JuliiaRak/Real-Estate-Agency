package com.solvd.persistence.mybatisImpl;

import com.solvd.domain.RealEstate;
import com.solvd.persistence.Config;
import com.solvd.persistence.repositories.RealEstateRepository;
import org.apache.ibatis.session.SqlSession;

public class RealEstateRepositoryMybatisImpl implements RealEstateRepository {
    @Override
    public void create(RealEstate realEstate, long clientId, long addressId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            realEstateRepository.create(realEstate, clientId, addressId);
        }
    }

    @Override
    public void deleteById(long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            realEstateRepository.deleteById(realEstateId);
        }
    }
}
