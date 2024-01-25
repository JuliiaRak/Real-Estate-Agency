package com.solvd.service;

import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;

import java.util.List;

public interface RealEstateService {
    void create(RealEstate realEstate, long clientId) throws EntityNotFoundException, LinkAlreadyExistsException;

    void deleteById(long realEstateId);

    void update(RealEstate realEstate);

    RealEstate getById(long realEstateId) throws EntityNotFoundException;

    List<RealEstate> getAll();

    boolean existsById(long id);
}
