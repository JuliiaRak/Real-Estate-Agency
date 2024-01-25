package com.solvd.service.impl;

import com.solvd.domain.Photo;
import com.solvd.domain.RealEstate;
import com.solvd.domain.Tag;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.service.AddressService;
import com.solvd.service.PhotoService;
import com.solvd.service.RealEstateService;
import com.solvd.service.TagService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {
    private final RealEstateRepository realEstateRepository;
    private final AddressService addressService;
    private final PhotoService photoService;
    private final TagService tagService;

    @Override
    public void create(RealEstate realEstate, long clientId) throws EntityNotFoundException, LinkAlreadyExistsException {
        realEstateCheck(realEstate);
        addressService.create(realEstate.getAddress());
        realEstateRepository.create(realEstate, clientId);
        for (Photo photo: realEstate.getPhotos()) {
            photoService.create(photo, realEstate.getId());
        }
        for (Tag tag: realEstate.getTags()) {
            tagService.assignToRealEstate(tag, realEstate.getId());
        }
    }

    @Override
    public void deleteById(long realEstateId) {
        realEstateRepository.deleteById(realEstateId);
    }

    @Override
    public void update(RealEstate realEstate) {
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

    private void realEstateCheck(RealEstate realEstate) {

    }
}
