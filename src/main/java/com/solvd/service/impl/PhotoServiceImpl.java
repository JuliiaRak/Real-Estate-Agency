package com.solvd.service.impl;

import com.solvd.domain.Photo;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.persistence.PhotoRepository;
import com.solvd.service.PhotoService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Override
    public void create(Photo photo, long realEstateId) throws LinkAlreadyExistsException {
        validate(photo);
        if (photoRepository.existsByLink(photo.getLink())) {
            throw new LinkAlreadyExistsException(String.format("This link is already exists: %s", photo.getLink()));
        }
        photoRepository.create(photo, realEstateId);
    }

    private void validate(Photo photo) {
        Validator<String> validator = new NotEmptyStringValidator(new NotNullStringValidator());
        validator.validate("photo link", photo.getLink());
    }

    @Override
    public void deleteByLink(String link) {
        photoRepository.deleteByLink(link);
    }

    @Override
    public void deleteByRealEstateId(long realEstateId) {
        photoRepository.deleteByRealEstateId(realEstateId);
    }

    @Override
    public List<Photo> findAllByRealEstateId(long realEstateId) {
        return photoRepository.findAllByRealEstateId(realEstateId);
    }
}
