package com.solvd.service.impl;

import com.solvd.domain.Photo;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.persistence.PhotoRepository;
import com.solvd.service.PhotoService;
import com.solvd.service.RealEstateService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final RealEstateService realEstateService;

    @Override
    public void create(Photo photo, long realEstateId) throws LinkAlreadyExistsException {
        if (photoRepository.existsByLink(photo.getLink())) {
            throw new LinkAlreadyExistsException(String.format("This link is already exists: %s", photo.getLink()));
        }
        if (realEstateService.getById(realEstateId).isEmpty()) {
            throw new EntityNotFoundException();//TODO
        }
        photoRepository.create(photo, realEstateId);
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
