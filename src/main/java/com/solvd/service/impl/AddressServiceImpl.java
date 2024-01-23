package com.solvd.service.impl;

import com.solvd.domain.Address;
import com.solvd.persistence.repositories.AddressRepository;
import com.solvd.service.AddressService;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void create(Address address) {
        addressRepository.create(address);
    }

    @Override
    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.getAll();
    }
}
