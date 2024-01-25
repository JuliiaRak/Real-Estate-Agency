package com.solvd.persistence.impl;

import com.solvd.domain.Tag;
import com.solvd.persistence.Config;
import com.solvd.persistence.TagRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TagRepositoryMybatisImpl implements TagRepository {

    @Override
    public void create(Tag tag) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);
            tagRepository.create(tag);
        }
    }

    @Override
    public void assignToRealEstate(Tag tag, long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);
            tagRepository.assignToRealEstate(tag, realEstateId);
        }
    }

    @Override
    public void deleteByName(String name) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);
            tagRepository.deleteByName(name);
        }
    }

    @Override
    public List<Tag> findAllByRealEstateId(long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);
            return tagRepository.findAllByRealEstateId(realEstateId);
        }
    }

    @Override
    public List<Tag> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);
            return tagRepository.findAll();
        }
    }

    @Override
    public boolean existsByName(String name) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);
            return tagRepository.existsByName(name);
        }
    }
}
