package com.solvd.service;

import com.solvd.domain.Client;
import com.solvd.domain.Meeting;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;

public interface MeetingService {
    void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException;

    void deleteById(long id);

    void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException;

    Meeting getById(long id) throws EntityNotFoundException;

    List<Meeting> getByClient(Client client);
    List<Meeting> getByRealEstate(RealEstate realEstate);
    List<Meeting> getAll();
}
