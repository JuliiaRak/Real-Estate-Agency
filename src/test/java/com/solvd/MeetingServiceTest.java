package com.solvd;

import com.solvd.domain.Client;
import com.solvd.domain.Employee;
import com.solvd.domain.Meeting;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.ClientService;
import com.solvd.service.EmployeeService;
import com.solvd.service.MeetingService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.EmployeeServiceImpl;
import com.solvd.service.impl.MeetingServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class MeetingServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(MeetingServiceTest.class);

    private final ClientService clientService = new ClientServiceImpl();
    private final RealEstateService realEstateService = new RealEstateServiceImpl();
    private final MeetingService meetingService = new MeetingServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    private Meeting createSampleMeeting() throws EntityNotFoundException {
        RealEstate realEstate = realEstateService.getAvailableById(2L);
        Client client = clientService.getById(1L);
        Employee employee = employeeService.getById(1L);
        return new Meeting(new Date(2024 - 1900, Calendar.APRIL, 22), new Date(), "status", realEstate, client, employee);
    }

    @Test
    public void createMeetingTest() {
        try {
            Meeting meeting = createSampleMeeting();
            meetingService.create(meeting, 2L, 1L, 1L);
            Meeting retrievedMeeting = meetingService.getById(meeting.getId());

            Assertions.assertNotNull(meeting.getId());
            Assertions.assertEquals(meeting.getMeetingStatus(), retrievedMeeting.getMeetingStatus());
            meetingService.deleteById(meeting.getId());
        } catch (EntityNotFoundException | FieldValidationException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteMeetingByIdTest() {
        try {
            Meeting meeting = createSampleMeeting();
            meetingService.create(meeting, 2L, 1L, 1L);
            meetingService.deleteById(meeting.getId());

            Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> {
                meetingService.getById(meeting.getId());
            });
        } catch (EntityNotFoundException | FieldValidationException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateMeetingTest() {
        try {
            Meeting meeting = createSampleMeeting();
            meetingService.create(meeting, 2L, 1L, 1L);
            meeting.setMeetingStatus("test status");
            meetingService.update(meeting, 2L, 1L, 1L);
            Meeting retrievedUpdatedMeeting = meetingService.getById(meeting.getId());

            Assertions.assertNotNull(retrievedUpdatedMeeting);
            Assertions.assertEquals("test status", retrievedUpdatedMeeting.getMeetingStatus());
            meetingService.deleteById(meeting.getId());
        } catch (EntityNotFoundException | FieldValidationException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getByIdMeetingTest() {
        try {
            Meeting meeting = createSampleMeeting();
            meetingService.create(meeting, 2L, 1L, 1L);
            Meeting retrievedMeeting = meetingService.getById(meeting.getId());

            Assertions.assertNotNull(retrievedMeeting);
            Assertions.assertEquals(meeting, retrievedMeeting);
            meetingService.deleteById(meeting.getId());
        } catch (EntityNotFoundException | FieldValidationException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
