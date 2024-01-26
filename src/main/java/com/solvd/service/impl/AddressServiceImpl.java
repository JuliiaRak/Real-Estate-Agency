package com.solvd.service.impl;

import com.solvd.domain.Address;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.AddressRepository;
import com.solvd.persistence.impl.AddressRepositoryMybatisImpl;
import com.solvd.service.AddressService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.SizeStringValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository = new AddressRepositoryMybatisImpl();

    @Override
    public void create(Address address) {
        validate(address);
        addressRepository.create(address);
    }

    @Override
    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void update(Address address) throws EntityNotFoundException {
        if (addressRepository.findById(address.getId()).isEmpty()) {
            throw new EntityNotFoundException("Address", address.getId());
        }
        validate(address);
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

    private void validate(Address address) {
        Validator<String> validator = new SizeStringValidator(new NotEmptyStringValidator(new NotNullStringValidator()));
        validator.validate("country", address.getCountry());
        validator.validate("region", address.getRegion());
        validator.validate("city", address.getCity());
        validator.validate("street", address.getStreet());
        validator.validate("building", address.getBuilding());
        validator.validate("apartment", address.getApartment());
    }
}
