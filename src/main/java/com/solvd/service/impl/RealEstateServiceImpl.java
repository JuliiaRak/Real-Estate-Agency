package com.solvd.service.impl;

import com.solvd.domain.RealEstate;
import com.solvd.persistence.repositories.RealEstateRepository;
import com.solvd.service.RealEstateService;

public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;

    public RealEstateServiceImpl(RealEstateRepository realEstateRepository) {
        this.realEstateRepository = realEstateRepository;
    }

    @Override
    public void create(RealEstate realEstate, long clientId, long addressId) {
        realEstateRepository.create(realEstate, clientId, addressId);
    }

    @Override
    public void deleteById(long realEstateId) {
        realEstateRepository.deleteById(realEstateId);
    }
}
