package com.solvd.service;

import com.solvd.domain.Tag;

import java.util.List;

public interface TagService {
    void create(Tag tag);

    void deleteByName(String name);

    List<Tag> getAll();
}
