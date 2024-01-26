package com.solvd.service.impl;

import com.solvd.domain.Meeting;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.MeetingRepository;
import com.solvd.service.*;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final RealEstateService realEstateService;

    @Override
    public void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) {
        validate(meeting);
        checkForeignKeysExistence(realEstateId, buyerId, employeeId);
        meetingRepository.create(meeting, realEstateId, buyerId, employeeId);
    }

    @Override
    public void deleteById(long id) {
        meetingRepository.deleteById(id);
    }

    @Override
    public void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException {
        validate(meeting);
        if (meetingRepository.findById(meeting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Meeting", meeting.getId());
        }
        meetingRepository.update(meeting, realEstateId, buyerId, employeeId);
    }

    @Override
    public Meeting getById(long id) throws EntityNotFoundException {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting", id));
    }

    @Override
    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    public void validate(Meeting meeting) {
        // Перевірка, що дата проведення зустрічі є у майбутньому
        if (meeting.getMeetingDateTime() == null || meeting.getMeetingDateTime().before(new Date())) {
            throw new IllegalArgumentException("Meeting date and time must be in the future");
        }

        // Перевірка, що дата подання запиту є у минулому
        if (meeting.getInquiryDate() == null || meeting.getInquiryDate().after(new Date())) {
            throw new IllegalArgumentException("Inquiry date cannot be in the future");
        }

        // Перевірка, що статус має допустимі значення (наприклад, "scheduled", "completed", тощо)
        if (meeting.getMeetingStatus() == null && !meeting.getMeetingStatus().isEmpty()) {
            throw new IllegalArgumentException("Invalid meeting status");
        }

    }

    void checkForeignKeysExistence(Long realEstateId, Long buyerId, Long employeeId){
        try {
            realEstateService.getById(realEstateId);
            clientService.getById(buyerId);
            employeeService.getById(employeeId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
