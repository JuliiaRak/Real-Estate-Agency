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
}
