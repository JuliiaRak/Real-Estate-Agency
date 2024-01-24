package com.solvd.persistence.impl;

import com.solvd.domain.Meeting;
import com.solvd.persistence.Config;
import com.solvd.persistence.MeetingRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class MeetingRepositoryMybatisImpl implements MeetingRepository {
    @Override
    public void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MeetingRepository meetingRepository = sqlSession.getMapper(MeetingRepository.class);
            meetingRepository.create(meeting, realEstateId, buyerId, employeeId);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MeetingRepository meetingRepository = sqlSession.getMapper(MeetingRepository.class);
            meetingRepository.deleteById(id);
        }
    }

    @Override
    public void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MeetingRepository meetingRepository = sqlSession.getMapper(MeetingRepository.class);
            meetingRepository.update(meeting, realEstateId, buyerId, employeeId);
        }
    }

    @Override
    public Optional<Meeting> findById(long id) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MeetingRepository meetingRepository = sqlSession.getMapper(MeetingRepository.class);
            return meetingRepository.findById(id);
        }
    }

    @Override
    public List<Meeting> findAll() {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MeetingRepository meetingRepository = sqlSession.getMapper(MeetingRepository.class);
            return meetingRepository.findAll();
        }
    }
}
