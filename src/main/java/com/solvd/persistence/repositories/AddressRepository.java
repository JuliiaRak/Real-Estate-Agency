package com.solvd.persistence.repositories;

import com.solvd.domain.Address;

public interface AddressRepository {
    void create(Address address);
    void deleteById(long id);
}
