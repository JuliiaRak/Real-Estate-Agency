package com.solvd.persistence;

import com.solvd.domain.RealEstate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface RealEstateRepository {
    void create(RealEstate realEstate);
    void deleteById(long realEstateId);
    void update(RealEstate realEstate);
    Optional<RealEstate> findById(long realEstateId);
    List<RealEstate> findAll();
}
