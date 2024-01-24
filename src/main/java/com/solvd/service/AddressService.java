package com.solvd.service;

import com.solvd.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void create(Address address);
    void deleteById(long id);
    Optional<Address> getById(long id);
    List<Address> getAll();
}
