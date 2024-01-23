package com.solvd.service;

import com.solvd.domain.RealEstate;

import java.util.List;

public interface RealEstateService {
    void create(RealEstate realEstate, long clientId, long addressId);
    void deleteById(long realEstateId);
    RealEstate findById(long realEstateId);
    List<RealEstate> getAll();
}
