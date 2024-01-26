package com.solvd.service.impl;

import com.solvd.domain.Tag;
import com.solvd.persistence.TagRepository;
import com.solvd.persistence.impl.TagRepositoryMybatisImpl;
import com.solvd.service.TagService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.SizeStringValidator;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl() {
        this.tagRepository = new TagRepositoryMybatisImpl();
    }

    @Override
    public void assignToRealEstate(Tag tag, long realEstateId) {
        validate(tag);
        if (!tagRepository.existsByName(tag.getName())) {
            tagRepository.create(tag);
        }
        tagRepository.assignToRealEstate(tag, realEstateId);
    }

    @Override
    public void allocateFromRealEstate(Tag tag, long realEstateId) {
        tagRepository.allocateFromRealEstate(tag, realEstateId);
    }

    private void validate(Tag tag) {
        Validator<String> validator = new SizeStringValidator(new NotEmptyStringValidator(new NotNullStringValidator()));
        validator.validate("Tag name", tag.getName());
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
