package com.solvd.persistence.impl;

import com.solvd.domain.Agreement;
import com.solvd.persistence.Config;
import com.solvd.persistence.AgreementRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AgreementRepositoryMyBatisImpl implements AgreementRepository {

    @Override
    public void create(Agreement agreement, long realEstateId, long clientId) {
        try(SqlSession sqlSession  = Config.getSessionFactory().openSession(true)){
            AgreementRepository agreementRepository = sqlSession.getMapper(AgreementRepository.class);
            agreementRepository.create(agreement, realEstateId, clientId);
        }
    }

    @Override
    public void deleteById(long id) {
        try(SqlSession sqlSession  = Config.getSessionFactory().openSession(true)){
            AgreementRepository agreementRepository = sqlSession.getMapper(AgreementRepository.class);
            agreementRepository.deleteById(id);
        }
    }

    @Override
    public void update(Agreement agreement) {
        try(SqlSession sqlSession  = Config.getSessionFactory().openSession(true)){
            AgreementRepository agreementRepository = sqlSession.getMapper(AgreementRepository.class);
            agreementRepository.update(agreement);
        }
    }

    @Override
    public Optional<Agreement> findById(long id) {
        try(SqlSession sqlSession  = Config.getSessionFactory().openSession(true)){
            AgreementRepository agreementRepository = sqlSession.getMapper(AgreementRepository.class);
            return agreementRepository.findById(id);
        }
    }

    @Override
    public List<Agreement> findAll() {
        try(SqlSession sqlSession  = Config.getSessionFactory().openSession(true)){
            AgreementRepository agreementRepository = sqlSession.getMapper(AgreementRepository.class);
             return agreementRepository.findAll();
        }
    }
}
