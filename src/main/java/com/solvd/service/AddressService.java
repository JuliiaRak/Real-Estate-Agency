package com.solvd.service;

import com.solvd.domain.Address;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;

public interface AddressService {
    void create(Address address);
    void deleteById(long id);
    void update(Address address);
    Address getById(long id) throws EntityNotFoundException;
    List<Address> getAll();
}
