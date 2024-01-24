package com.solvd.persistence;

import com.solvd.domain.Address;
import org.apache.ibatis.annotations.Param;

public interface RealEstateAddressMapper {
    void create(@Param("address") Address address);
}
