package com.solvd.service;

import com.solvd.domain.Meeting;

import java.util.List;
import java.util.Optional;

public interface MeetingService {
    void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId);
    void deleteById(long id);
    void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId);
    Optional<Meeting> getById(long id);
    List<Meeting> getAll();
}
