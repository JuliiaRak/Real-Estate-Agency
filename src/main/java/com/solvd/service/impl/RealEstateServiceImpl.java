package com.solvd.service.impl;

import com.solvd.domain.Photo;
import com.solvd.domain.RealEstate;
import com.solvd.domain.Tag;
import com.solvd.domain.exceptions.EntityNotFoundException;
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

@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final AddressService addressService;
    private final PhotoService photoService;
    private final TagService tagService;

    public RealEstateServiceImpl() {
        this.realEstateRepository = new RealEstateRepositoryMybatisImpl();
        this.addressService = new AddressServiceImpl();
        this.photoService = new PhotoServiceImpl();
        this.tagService = new TagServiceImpl();
    }

    @Override
    public void create(RealEstate realEstate, long clientId) throws EntityNotFoundException, LinkAlreadyExistsException {
        validate(realEstate);
        addressService.create(realEstate.getAddress());
        realEstateRepository.create(realEstate, clientId);
        for (Photo photo : realEstate.getPhotos()) {
            photoService.create(photo, realEstate.getId());
        }
        for (Tag tag : realEstate.getTags()) {
            tagService.assignToRealEstate(tag, realEstate.getId());
        }
    }

    @Override
    public void deleteById(long realEstateId) {
        realEstateRepository.deleteById(realEstateId);
    }

    @Override
    public void update(RealEstate realEstate) {
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
    public boolean existsById(long id) {
        return realEstateRepository.findById(id).isPresent();
    }

    public void validate(RealEstate realEstate) {
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