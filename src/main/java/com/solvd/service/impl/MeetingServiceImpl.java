package com.solvd.service.impl;

import com.solvd.domain.Meeting;
import com.solvd.persistence.MeetingRepository;
import com.solvd.service.MeetingService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    @Override
    public void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) {
        meetingRepository.create(meeting, realEstateId, buyerId, employeeId);
    }

    @Override
    public void deleteById(long id) {
        meetingRepository.deleteById(id);
    }

    @Override
    public void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) {
        meetingRepository.update(meeting, realEstateId, buyerId, employeeId);
    }

    @Override
    public Optional<Meeting> getById(long id) {
        return meetingRepository.findById(id);
    }

    @Override
    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }
}
