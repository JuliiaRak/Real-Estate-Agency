package com.solvd.service.impl;

import com.solvd.domain.Meeting;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.MeetingRepository;
import com.solvd.service.MeetingService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.date.FutureDateValidator;
import com.solvd.service.validators.date.NotNullDateValidator;
import com.solvd.service.validators.date.PastDateValidator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.SizeStringValidator;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    @Override
    public void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) {
        validate(meeting);
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

    private void validate(Meeting meeting) {
        Validator<Date> notNullDateValidator = new NotNullDateValidator();
        Validator<Date> futureDateValidator = new FutureDateValidator(notNullDateValidator);
        futureDateValidator.validate("meeting date", meeting.getMeetingDateTime());

        Validator<Date> pastDateValidator = new PastDateValidator(notNullDateValidator);
        pastDateValidator.validate("inquiry date", meeting.getInquiryDate());

        Validator<String> stringValidator = new SizeStringValidator(new NotEmptyStringValidator(new NotNullStringValidator()));
        stringValidator.validate("meeting status", meeting.getMeetingStatus());
    }
}
