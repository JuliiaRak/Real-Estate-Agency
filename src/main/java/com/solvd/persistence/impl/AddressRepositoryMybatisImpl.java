package com.solvd.persistence.impl;

import com.solvd.domain.Address;
import com.solvd.persistence.Config;
import com.solvd.persistence.AddressRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AddressRepositoryMybatisImpl implements AddressRepository {
    @Override
    public void create(Address address) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.create(address);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.deleteById(id);
        }
    }

    @Override
    public void update(Address address) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.update(address);
        }
    }

    @Override
    public Optional<Address> findById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            return addressRepository.findById(id);
        }
    }

    @Override
    public List<Address> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            return addressRepository.findAll();
        }
    }
}
