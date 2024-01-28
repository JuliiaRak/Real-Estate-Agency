package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.Meeting;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MeetingService {
    void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException;

    void deleteById(long id);

    void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException;

    Meeting getById(long id) throws EntityNotFoundException;

    Meeting getByClient(Client client) throws EntityNotFoundException;
    List<Meeting> getAll();
}
