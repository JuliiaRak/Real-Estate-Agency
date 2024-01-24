package com.solvd;

import com.solvd.domain.Employee;
import com.solvd.domain.Meeting;
import com.solvd.persistence.impl.EmployeeRepositoryMybatisImpl;
import com.solvd.persistence.impl.MeetingRepositoryMybatisImpl;
import com.solvd.service.EmployeeService;
import com.solvd.service.MeetingService;
import com.solvd.service.impl.EmployeeServiceImpl;
import com.solvd.service.impl.MeetingServiceImpl;

import java.util.Date;

public class MeetingTest {
    public static void main(String[] args) {
        MeetingService meetingService = new MeetingServiceImpl(new MeetingRepositoryMybatisImpl());

        Meeting meeting = new Meeting(0L, new Date(), new Date(), "status", null, null, null);
        meetingService.create(meeting, 2L, 6L, 10L);
        System.out.println(meetingService.getById(meeting.getId()));

//        employee.setFirstName("Olena");
//        employeeService.update(employee);
//        System.out.println(employeeService.getById(employee.getId()));
//        System.out.println(employeeService.getAll());
//
//        //employeeService.deleteById(employee.getId());
    }
}
