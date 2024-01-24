package com.solvd.persistence.repositories.mybatisImpl;

import com.solvd.domain.Photo;
import com.solvd.persistence.Config;
import com.solvd.persistence.repositories.PhotoRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PhotoRepositoryMybatisImpl implements PhotoRepository {
    @Override
    public void create(Photo photo, long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PhotoRepository photoRepository = sqlSession.getMapper(PhotoRepository.class);
            photoRepository.create(photo, realEstateId);
        }
    }

    @Override
    public void deleteByRealEstateId(long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PhotoRepository photoRepository = sqlSession.getMapper(PhotoRepository.class);
            photoRepository.deleteByRealEstateId(realEstateId);
        }
    }

    @Override
    public List<Photo> findAllByRealEstateId(long realEstateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PhotoRepository photoRepository = sqlSession.getMapper(PhotoRepository.class);
            return photoRepository.findAllByRealEstateId(realEstateId);
        }
    }

    @Override
    public List<Photo> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PhotoRepository photoRepository = sqlSession.getMapper(PhotoRepository.class);
            return photoRepository.findAll();
        }
    }
}
