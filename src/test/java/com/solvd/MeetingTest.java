package com.solvd;

import com.solvd.domain.*;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.service.*;
import com.solvd.service.impl.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MeetingTest {
    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl();
    private static final AddressService ADDRESS_SERVICE = new AddressServiceImpl();
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();
    private static final AgreementService AGREEMENT_SERVICE = new AgreementServiceImpl();
    private static final MeetingService MEETING_SERVICE = new MeetingServiceImpl();
    private static final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl();

    public static void main(String[] args) throws EntityNotFoundException {
//        MeetingService meetingService = new MeetingServiceImpl();
//
//        Meeting meeting = new Meeting(new Date(2024-1900, Calendar.APRIL, 22), new Date(), "status", null, null, null);
//        meetingService.create(meeting, 2L, 6L, 10L);
//        try {
//            System.out.println(meetingService.getById(1));
//        } catch (EntityNotFoundException e) {
//            throw new RuntimeException(e);
//        }

//        employee.setFirstName("Olena");
//        employeeService.update(employee);
//        System.out.println(employeeService.getById(employee.getId()));
//        System.out.println(employeeService.getAll());
//
//        //employeeService.deleteById(employee.getId());





        List<Agreement> agreements = AGREEMENT_SERVICE.getAll();
        System.out.println(Agreement.getTableHeader());
        for (Agreement agreement : agreements) {
            System.out.println(agreement);
        }
        System.out.println();

        List<Client> clients = CLIENT_SERVICE.getAll();
        System.out.println(Client.getTableHeader());
        for (Client client : clients) {
            System.out.println(client);
        }
        System.out.println();

        List<Employee> employees = EMPLOYEE_SERVICE.getAll();
        System.out.println(Employee.getTableHeader());
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println();

        List<Meeting> meetings = MEETING_SERVICE.getAll();
        System.out.println(Meeting.getTableHeader());
        for (Meeting meeting : meetings) {
            System.out.println(meeting);
        }
        System.out.println();

        List<RealEstate> realEstates = REAL_ESTATE_SERVICE.getAll();
        System.out.println(RealEstate.getTableHeader());
        for (RealEstate realEstate : realEstates) {
            System.out.println(realEstate);
        }
        System.out.println();
    }
}
