package com.solvd.persistence;

import com.solvd.domain.Tag;

import java.util.List;

public interface TagRepository {
    void create(Tag tag);

    void deleteByName(String name);

    List<Tag> findAllByRealEstateId(long realEstateId);

    List<Tag> findAll();
}
