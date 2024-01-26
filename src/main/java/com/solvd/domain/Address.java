package com.solvd.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Address {
    private long id;
    private String country;
    private String region;
    private String city;
    private String street;
    private String building;
    private String apartment;
}
