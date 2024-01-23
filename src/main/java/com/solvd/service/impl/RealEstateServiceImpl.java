package com.solvd.service.impl;

import com.solvd.domain.RealEstate;
import com.solvd.persistence.repositories.RealEstateRepository;
import com.solvd.service.RealEstateService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<RealEstate> findById(long realEstateId) {
        return realEstateRepository.findById(realEstateId);
    }

    @Override
    public List<RealEstate> getAll() {
        return realEstateRepository.getAll();
    }
}
