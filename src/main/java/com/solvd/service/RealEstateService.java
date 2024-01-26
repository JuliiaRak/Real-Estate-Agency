package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RealEstateService {
    void create(RealEstate realEstate, long clientId);
    void deleteById(long realEstateId);
    void update(RealEstate realEstate);
    RealEstate getById(long realEstateId) throws EntityNotFoundException;
    List<RealEstate> getAll();
    List<RealEstate> getAllBySeller(Client seller);
    List<RealEstate> getAllByType(RealEstateType realEstateType);
}
