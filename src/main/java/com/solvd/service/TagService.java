package com.solvd.service;

import com.solvd.domain.Tag;
import com.solvd.domain.exceptions.FieldValidationException;

import java.util.List;

public interface TagService {
    void assignToRealEstate(Tag tag, long realEstateId) throws FieldValidationException;

    void allocateFromRealEstate(Tag tag, long realEstateId);

    void deleteByName(String name);

    List<Tag> getAll();
}
