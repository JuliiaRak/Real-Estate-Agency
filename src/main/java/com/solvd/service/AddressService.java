package com.solvd.service;

import com.solvd.domain.Address;

public interface AddressService {
    void create(Address address);
    void deleteById(long id);
}
