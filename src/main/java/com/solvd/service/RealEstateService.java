package com.solvd.service;

import com.solvd.domain.RealEstate;

import java.util.List;
import java.util.Optional;

public interface RealEstateService {
    void create(RealEstate realEstate, long clientId, long addressId);
    void deleteById(long realEstateId);
    Optional<RealEstate> findById(long realEstateId);
    List<RealEstate> getAll();
}
