package com.solvd.persistence.repositories;

import com.solvd.domain.Address;

import java.util.List;

public interface AddressRepository {
    void create(Address address);
    void deleteById(long id);
    Address findById(long id);
    List<Address> getAll();
}
