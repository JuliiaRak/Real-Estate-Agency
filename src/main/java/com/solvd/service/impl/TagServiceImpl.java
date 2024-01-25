package com.solvd.service.impl;

import com.solvd.domain.Tag;
import com.solvd.persistence.TagRepository;
import com.solvd.service.TagService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public void assignToRealEstate(Tag tag, long realEstateId) {
        if (!tagRepository.existsByName(tag.getName())) {
            tagRepository.create(tag);
        }
        tagRepository.assignToRealEstate(tag, realEstateId);
    }

    @Override
    public void deleteByName(String name) {
        tagRepository.deleteByName(name);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
