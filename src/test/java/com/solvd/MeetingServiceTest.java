package com.solvd;

import com.solvd.domain.Client;
import com.solvd.domain.Employee;
import com.solvd.domain.Meeting;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.*;
import com.solvd.service.impl.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MeetingServiceTest {

    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl();
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();
    private static final MeetingService MEETING_SERVICE = new MeetingServiceImpl();
    private static final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl();

    private Meeting createSampleMeeting() throws EntityNotFoundException {
        RealEstate realEstate = REAL_ESTATE_SERVICE.getAvailableById(2L);
        Client client = CLIENT_SERVICE.getById(1L);
        Employee employee = EMPLOYEE_SERVICE.getById(1L);
        return new Meeting(new Date(2024-1900, Calendar.APRIL, 22), new Date(), "status", realEstate, client, employee);
    }

    @Test
    public void createMeetingTest() {
        try {
            Meeting meeting = createSampleMeeting();
            MEETING_SERVICE.create(meeting, 2L, 1L, 1L);
            Meeting retrievedMeeting = MEETING_SERVICE.getById(meeting.getId());

            Assertions.assertNotNull(meeting.getId());
            Assertions.assertEquals(meeting.getMeetingStatus(), retrievedMeeting.getMeetingStatus());
            MEETING_SERVICE.deleteById(meeting.getId());
        } catch (EntityNotFoundException | FieldValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteMeetingByIdTest() {
        try {
            Meeting meeting = createSampleMeeting();
            MEETING_SERVICE.create(meeting, 2L, 1L, 1L);
            MEETING_SERVICE.deleteById(meeting.getId());

            Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> {
                MEETING_SERVICE.getById(meeting.getId());
            });
        } catch (EntityNotFoundException | FieldValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateMeetingTest() {
        try {
            Meeting meeting = createSampleMeeting();
            MEETING_SERVICE.create(meeting, 2L, 1L, 1L);
            meeting.setMeetingStatus("test status");
            MEETING_SERVICE.update(meeting, 2L, 1L, 1L);
            Meeting retrievedUpdatedMeeting = MEETING_SERVICE.getById(meeting.getId());

            Assertions.assertNotNull(retrievedUpdatedMeeting);
            Assertions.assertEquals("test status", retrievedUpdatedMeeting.getMeetingStatus());
            MEETING_SERVICE.deleteById(meeting.getId());
        } catch (EntityNotFoundException | FieldValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getByIdMeetingTest() {
        try {
            Meeting meeting = createSampleMeeting();
            MEETING_SERVICE.create(meeting, 2L, 1L, 1L);
            Meeting retrievedMeeting = MEETING_SERVICE.getById(meeting.getId());

            Assertions.assertNotNull(retrievedMeeting);
            Assertions.assertEquals(meeting, retrievedMeeting);
            MEETING_SERVICE.deleteById(meeting.getId());
        } catch (EntityNotFoundException | FieldValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
