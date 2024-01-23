package com.solvd.persistence.repositories;

import com.solvd.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    void create(Address address);
    void deleteById(long id);
    Optional<Address> findById(long id);
    List<Address> getAll();
}
