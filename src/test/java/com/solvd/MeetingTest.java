package com.solvd;

import com.solvd.domain.Employee;
import com.solvd.domain.Meeting;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.impl.*;
import com.solvd.service.EmployeeService;
import com.solvd.service.MeetingService;
import com.solvd.service.impl.*;

import java.util.Calendar;
import java.util.Date;

public class MeetingTest {
    public static void main(String[] args) {
        MeetingService meetingService = new MeetingServiceImpl(new MeetingRepositoryMybatisImpl(),
                new ClientServiceImpl(new ClientRepositoryMybatisImpl()),
                new EmployeeServiceImpl(new EmployeeRepositoryMybatisImpl()),
                new RealEstateServiceImpl(new RealEstateRepositoryMybatisImpl(), new AddressServiceImpl(new AddressRepositoryMybatisImpl())));

        Meeting meeting = new Meeting(new Date(2024-1900, Calendar.APRIL, 22), new Date(), "status", null, null, null);
        meetingService.create(meeting, 2L, 6L, 10L);
//        try {
//            System.out.println(meetingService.getById(meeting.getId()));
//        } catch (EntityNotFoundException e) {
//            throw new RuntimeException(e);
//        }

//        employee.setFirstName("Olena");
//        employeeService.update(employee);
//        System.out.println(employeeService.getById(employee.getId()));
//        System.out.println(employeeService.getAll());
//
//        //employeeService.deleteById(employee.getId());
    }
}
