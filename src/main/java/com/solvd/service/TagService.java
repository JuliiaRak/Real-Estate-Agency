package com.solvd.service;

import com.solvd.domain.Tag;

import java.util.List;

public interface TagService {
    void assignToRealEstate(Tag tag, long realEstateId);

    void allocateFromRealEstate(Tag tag, long realEstateId);

    void deleteByName(String name);

    List<Tag> getAll();
}
