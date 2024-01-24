package com.solvd.service.impl;

import com.solvd.domain.RealEstate;
import com.solvd.persistence.repositories.RealEstateRepository;
import com.solvd.service.RealEstateService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;

    @Override
    public void create(RealEstate realEstate, long clientId, long addressId) {
        realEstateRepository.create(realEstate, clientId, addressId);
    }

    @Override
    public void deleteById(long realEstateId) {
        realEstateRepository.deleteById(realEstateId);
    }

    @Override
    public void update(RealEstate realEstate) {
        realEstateRepository.update(realEstate);
    }

    @Override
    public Optional<RealEstate> getById(long realEstateId) {
        return realEstateRepository.findById(realEstateId);
    }

    @Override
    public List<RealEstate> getAll() {
        return realEstateRepository.findAll();
    }
}
