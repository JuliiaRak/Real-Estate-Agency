package com.solvd.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
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

    @Override
    public String toString() {
        return "RealEstate{" +
                "id=" + id +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", realEstateDescription='" + realEstateDescription + '\'' +
                ", realEstateType='" + realEstateType + '\'' +
                ", metrics='" + metrics + '\'' +
                ", rooms=" + rooms +
                ", address=" + address +
                ", seller=" + seller +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealEstate that = (RealEstate) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
