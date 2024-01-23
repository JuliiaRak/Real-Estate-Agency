package com.solvd.domain;

import java.math.BigDecimal;
import java.util.Objects;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getRealEstateDescription() {
        return realEstateDescription;
    }

    public void setRealEstateDescription(String realEstateDescription) {
        this.realEstateDescription = realEstateDescription;
    }

    public String getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(String realEstateType) {
        this.realEstateType = realEstateType;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Client getSeller() {
        return seller;
    }

    public void setSeller(Client seller) {
        this.seller = seller;
    }

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
        return id == that.id && isAvailable == that.isAvailable && rooms == that.rooms && Objects.equals(price, that.price) && Objects.equals(realEstateDescription, that.realEstateDescription) && Objects.equals(realEstateType, that.realEstateType) && Objects.equals(metrics, that.metrics) && Objects.equals(address, that.address) && Objects.equals(seller, that.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, isAvailable, realEstateDescription, realEstateType, metrics, rooms, address, seller);
    }
}
