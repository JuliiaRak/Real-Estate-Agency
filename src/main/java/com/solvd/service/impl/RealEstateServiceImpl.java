package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.service.AddressService;
import com.solvd.service.RealEstateService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<RealEstate> getAllBySeller(Client seller) {
        return realEstateRepository.findAll().stream()
                .filter(realEstate -> realEstate.getSeller().getId() == seller.getId())
                .collect(Collectors.toList());
    }
    @Override
    public List<RealEstate> getAllByType(RealEstateType realEstateType){
        return realEstateRepository.findAll().stream()
                .filter(realEstate -> realEstate.getRealEstateType() == realEstateType)
                .collect(Collectors.toList());
    }



    private void realEstateCheck(RealEstate realEstate) {

    }
}
