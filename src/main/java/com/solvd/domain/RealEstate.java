package com.solvd.domain;

import com.solvd.domain.enums.RealEstateType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private List<Photo> photos = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public static String getTableHeader() {
        return String.format("\033[1m| %-3s | %-10s | %-31s | %-9s | %-7s | %-5s | %-8s | %-10s\033[0m",
                "ID", "Type", "Description", "Price", "Metrics", "Rooms", "SellerID", "Address");
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %-10s | %-31s | %-9s | %-7s | %-5s | %-8s | %-30s",
                id, realEstateType, description, price, metrics, rooms, seller.getId(), address);
    }
}
