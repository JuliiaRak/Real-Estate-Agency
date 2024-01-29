package com.solvd.service;

import com.solvd.domain.Address;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;

import java.util.List;

public interface AddressService {
    void create(Address address) throws FieldValidationException;
    void deleteById(long id);
    void update(Address address) throws EntityNotFoundException, FieldValidationException;
    Address getById(long id) throws EntityNotFoundException;
    List<Address> getAll();
}
