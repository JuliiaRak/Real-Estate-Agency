package com.solvd.persistence;

import com.solvd.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagRepository {
    void create(Tag tag);

    void assignToRealEstate(@Param("tag") Tag tag, @Param("realEstateId") long realEstateId);

    void allocateFromRealEstate(@Param("tag") Tag tag, @Param("realEstateId") long realEstateId);

    void deleteByName(String name);

    List<Tag> findAllByRealEstateId(long realEstateId);

    List<Tag> findAll();

    boolean existsByName(String name);
}
