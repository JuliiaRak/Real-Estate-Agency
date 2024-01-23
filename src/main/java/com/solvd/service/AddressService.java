package com.solvd.service;

import com.solvd.domain.Address;

import java.util.List;

public interface AddressService {
    void create(Address address);
    void deleteById(long id);
    Address findById(long id);
    List<Address> getAll();
}
