package com.solvd.service.impl;

import com.solvd.domain.Address;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.AddressRepository;
import com.solvd.service.AddressService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public void create(Address address) {
        addressCheck(address);
        addressRepository.create(address);
    }

    @Override
    public void deleteById(long id) {
        if (addressRepository.findById(id).isEmpty()) {
            throw new RuntimeException("The address is not in the database");
        }
        addressRepository.deleteById(id);
    }

    @Override
    public void update(Address address) {
        if (addressRepository.findById(address.getId()).isEmpty()) {
            throw new RuntimeException("Address is not in the database");
        }
        addressCheck(address);
        addressRepository.update(address);
    }

    @Override
    public Address getById(long id) throws EntityNotFoundException {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address", id));
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    private void addressCheck(Address address) {
        if (address.getCountry() == null) {
            throw new IllegalArgumentException("The country cannot be null");
        }
        if (address.getCountry().isEmpty()) {
            throw new IllegalArgumentException("The country cannot be empty");
        }
        if (address.getRegion() == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        if (address.getRegion().isEmpty()) {
            throw new IllegalArgumentException("Region cannot be empty");
        }
        if (address.getCity() == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        if (address.getCity().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        if (address.getRegion() == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        if (address.getRegion().isEmpty()) {
            throw new IllegalArgumentException("Region cannot be empty");
        }
        if (address.getRegion() == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        if (address.getRegion().isEmpty()) {
            throw new IllegalArgumentException("Region cannot be empty");
        }
        if (address.getRegion() == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        if (address.getRegion().isEmpty()) {
            throw new IllegalArgumentException("Region cannot be empty");
        }

    }
}
