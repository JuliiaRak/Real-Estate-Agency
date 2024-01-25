package com.solvd.service.impl;

import com.solvd.domain.Agreement;
import com.solvd.persistence.AgreementRepository;
import com.solvd.service.AgreementService;

import java.util.List;
import java.util.Optional;

public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;

    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public void create(Agreement agreement, long realEstateId, long clientId) {
        agreementRepository.create(agreement, realEstateId, clientId);
    }

    @Override
    public void deleteById(long id) {
        agreementRepository.deleteById(id);
    }

    @Override
    public void update(Agreement agreement) {
        agreementRepository.update(agreement);
    }

    @Override
    public Optional<Agreement> getById(long id) {
        return agreementRepository.findById(id);
    }

    @Override
    public List<Agreement> getAll() {
        return agreementRepository.findAll();
    }
}