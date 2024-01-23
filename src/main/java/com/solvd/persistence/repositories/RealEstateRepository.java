package com.solvd.persistence.repositories;

import com.solvd.domain.RealEstate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface RealEstateRepository {
    void create(@Param("realEstate") RealEstate realEstate, @Param("clientId") long clientId,
                @Param("addressId") long addressId);
    void deleteById(long realEstateId);
    Optional<RealEstate> findById(long realEstateId);
    List<RealEstate> getAll();
}
