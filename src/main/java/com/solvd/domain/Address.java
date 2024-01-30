package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Address {
    private long id;
    private String country;
    private String region;
    private String city;
    private String street;
    private String building;
    private String apartment;

    @Override
    public String toString() {
        return String.format("%s, %s %s, %s %s %s",
                country, region, city, street, building, apartment);
    }
}
