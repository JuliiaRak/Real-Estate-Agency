package com.solvd.persistence.impl;

import com.solvd.domain.RealEstate;
import com.solvd.persistence.AddressRepository;
import com.solvd.persistence.Config;
import com.solvd.persistence.RealEstateAddressMapper;
import com.solvd.persistence.RealEstateRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class RealEstateRepositoryMybatisImpl implements RealEstateRepository {
    @Override
    public void create(RealEstate realEstate) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            addressRepository.create(realEstate.getAddress());
            realEstateRepository.create(realEstate);
        }
    }

    @Override
    public void deleteById(long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            realEstateRepository.deleteById(realEstateId);
        }
    }

    @Override
    public void update(RealEstate realEstate) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            realEstateRepository.update(realEstate);
        }
    }

    @Override
    public Optional<RealEstate> findById(long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            return realEstateRepository.findById(realEstateId);
        }
    }

    @Override
    public List<RealEstate> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            RealEstateRepository realEstateRepository = sqlSession.getMapper(RealEstateRepository.class);
            return realEstateRepository.findAll();
        }
    }
}
