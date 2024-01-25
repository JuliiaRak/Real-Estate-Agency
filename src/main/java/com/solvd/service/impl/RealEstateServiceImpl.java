package com.solvd.service.impl;

import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.service.AddressService;
import com.solvd.service.RealEstateService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final AddressService addressService;

    @Override
    public void create(RealEstate realEstate, long clientId) {
        realEstateCheck(realEstate);
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
    public RealEstate getById(long realEstateId) throws EntityNotFoundException {
        return realEstateRepository.findById(realEstateId).orElseThrow(() -> new EntityNotFoundException("RealEstate", realEstateId));
    }

    @Override
    public List<RealEstate> getAll() {
        return realEstateRepository.findAll();
    }

    private void realEstateCheck(RealEstate realEstate) {
        if (realEstate == null) {
            throw new IllegalArgumentException("RealEstate cannot be null");
        }
        if (realEstate.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (realEstate.getDescription() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (realEstate.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (realEstate.getRealEstateType() == null) {
            throw new IllegalArgumentException("RealEstateType cannot be null");
        }
        if (realEstate.getMetrics() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (realEstate.getMetrics().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }
}
