package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
public class RealEstate {
    private long id;
    private BigDecimal price;
    private boolean isAvailable;
    private String realEstateDescription;
    private String realEstateType;
    private String metrics;
    private int rooms;
    private Address address;
    private Client seller;
}
