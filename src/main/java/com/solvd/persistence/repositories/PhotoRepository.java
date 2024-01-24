package com.solvd.persistence.repositories;

import com.solvd.domain.Photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoRepository {
    void create(@Param("photo") Photo photo, @Param("realEstateId") long realEstateId);

    void deleteByRealEstateId(long realEstateId);

    List<Photo> findAllByRealEstateId(long realEstateId);

    List<Photo> findAll();
}
