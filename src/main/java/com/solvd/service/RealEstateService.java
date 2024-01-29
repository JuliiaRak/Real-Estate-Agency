package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;

import java.util.List;

public interface RealEstateService {
    void create(RealEstate realEstate, long clientId) throws FieldValidationException;

    void deleteById(long realEstateId);

    void update(RealEstate realEstate) throws EntityNotFoundException, FieldValidationException;

    RealEstate getById(long realEstateId) throws EntityNotFoundException;

    List<RealEstate> getAll();
    List<RealEstate> getAllBySeller(Client seller);
    List<RealEstate> getAllByType(RealEstateType realEstateType);

    boolean existsById(long id);
    void hideById(long id) throws EntityNotFoundException;

}