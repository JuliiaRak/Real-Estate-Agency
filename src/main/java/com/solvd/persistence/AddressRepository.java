package com.solvd.persistence;

import com.solvd.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    void create(Address address);

    void deleteById(long id);

    void update(Address address);

    Optional<Address> findById(long id);

    List<Address> findAll();
}
