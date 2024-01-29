package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Photo;
import com.solvd.domain.RealEstate;
import com.solvd.domain.Tag;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.persistence.impl.RealEstateRepositoryMybatisImpl;
import com.solvd.service.AddressService;
import com.solvd.service.PhotoService;
import com.solvd.service.RealEstateService;
import com.solvd.service.TagService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.bigint.MaxLongValidator;
import com.solvd.service.validators.bigint.MinLongValidator;
import com.solvd.service.validators.integer.MinIntegerValidator;
import com.solvd.service.validators.integer.NotNegativeIntegerValidator;
import com.solvd.service.validators.object.NotNullObjectValidator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.SizeStringValidator;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final AddressService addressService;

    public RealEstateServiceImpl() {
        this.realEstateRepository = new RealEstateRepositoryMybatisImpl();
        this.addressService = new AddressServiceImpl();
    }

    @Override
    public void create(RealEstate realEstate, long clientId) throws FieldValidationException {
        validate(realEstate);
        addressService.create(realEstate.getAddress());
        realEstateRepository.create(realEstate, clientId);
    }

    @Override
    public void deleteById(long realEstateId) {
        realEstateRepository.deleteById(realEstateId);
    }

    @Override
    public void update(RealEstate realEstate) throws EntityNotFoundException, FieldValidationException {
        if (realEstateRepository.findById(realEstate.getId()).isEmpty()) {
            throw new EntityNotFoundException("Real Estate", realEstate.getId());
        }
        validate(realEstate);
        realEstateRepository.update(realEstate);
    }

    @Override
    public RealEstate getById(long realEstateId) throws EntityNotFoundException {
        return realEstateRepository.findById(realEstateId).orElseThrow(() -> new EntityNotFoundException("RealEstate", realEstateId));
    }

    @Override
    public List<RealEstate> getAll() {
        return realEstateRepository.findAll();
    }
    @Override
    public List<RealEstate> getAllBySeller(Client seller) {
        return realEstateRepository.findAll().stream()
                .filter(realEstate -> realEstate.getSeller().getId() == seller.getId())
                .collect(Collectors.toList());
    }
    @Override
    public List<RealEstate> getAllByType(RealEstateType realEstateType){
        return realEstateRepository.findAll().stream()
                .filter(realEstate -> realEstate.getRealEstateType() == realEstateType)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(long id) {
        return realEstateRepository.findById(id).isPresent();
    }

    @Override
    public void hideById(long id) throws EntityNotFoundException {
        RealEstate realEstate = getById(id);
        realEstate.setAvailable(false);
        realEstateRepository.update(realEstate);
    }

    public void validate(RealEstate realEstate) throws FieldValidationException {
        Validator<Object> objectValidator = new NotNullObjectValidator();
        objectValidator.validate("real estate", realEstate);
        objectValidator.validate("price", realEstate.getPrice());

        Validator<String> stringValidator = new SizeStringValidator(15, new NotEmptyStringValidator(new NotNullStringValidator()));
        stringValidator.validate("metrics", realEstate.getMetrics());

        Validator<Long> longValidator = new MaxLongValidator(new MinLongValidator());
        longValidator.validate("price", realEstate.getPrice().longValue());
        Validator<Integer> intValidator = new MinIntegerValidator(1, new NotNegativeIntegerValidator());
        intValidator.validate("rooms", realEstate.getRooms());
    }
}