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
        addressRepository.deleteById(id);
    }

    @Override
    public void update(Address address) throws EntityNotFoundException {
        if (addressRepository.findById(address.getId()).isEmpty()) {
            throw new EntityNotFoundException("Address");
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

    }
}
