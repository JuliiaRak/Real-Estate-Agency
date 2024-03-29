package com.solvd.service;

import com.solvd.domain.Photo;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;

import java.util.List;

public interface PhotoService {
    void create(Photo photo, long realEstateId) throws LinkAlreadyExistsException, FieldValidationException;

    void deleteByLink(String link);

    void deleteByRealEstateId(long realEstateId);

    List<Photo> findAllByRealEstateId(long realEstateId);
}
