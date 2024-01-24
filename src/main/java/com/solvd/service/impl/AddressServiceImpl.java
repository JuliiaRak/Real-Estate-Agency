package com.solvd.service.impl;

import com.solvd.domain.Address;
import com.solvd.persistence.repositories.AddressRepository;
import com.solvd.service.AddressService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public void create(Address address) {
        addressRepository.create(address);
    }

    @Override
    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void update(Address address) {
        addressRepository.update(address);
    }

    @Override
    public Optional<Address> getById(long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
