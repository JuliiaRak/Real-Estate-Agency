package com.solvd.domain;

import com.solvd.domain.enums.RealEstateType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
public class RealEstate {
    private long id;
    private BigDecimal price;
    private boolean isAvailable;
    private String description;
    private RealEstateType realEstateType;
    private String metrics;
    private int rooms;
    private Address address;
    private Client seller;
}
