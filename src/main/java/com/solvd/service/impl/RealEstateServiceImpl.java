package com.solvd.service.impl;

import com.solvd.domain.RealEstate;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.service.AddressService;
import com.solvd.service.RealEstateService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final AddressService addressService;

    @Override
    public void create(RealEstate realEstate, long clientId) {
        addressService.create(realEstate.getAddress());
        realEstateRepository.create(realEstate, clientId);
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
