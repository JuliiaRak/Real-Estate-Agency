package com.solvd;

import com.solvd.domain.Client;
import com.solvd.domain.Employee;
import com.solvd.domain.Meeting;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.MeetingService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.MeetingServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MeetingAction {
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();
    private static final MeetingService MEETING_SERVICE = new MeetingServiceImpl();

    public static void createMeeting(Scanner scanner, Client client, Employee employee) {
        Meeting meeting = new Meeting();
        RealEstate realEstate = null;

        System.out.println("\nHere list of all available Real Estates");
        List<RealEstate> realEstates = REAL_ESTATE_SERVICE.getAllAvailable();
        System.out.println(RealEstate.getTableHeader());
        for (RealEstate availableRealEstate : realEstates) {
            System.out.println(availableRealEstate);
        }
        System.out.print("Enter the id of Real Estate you want to view: ");
        String realEstateString = scanner.nextLine();

        System.out.println("Enter date in the yyyy-MM-dd format when you want to make a view");
        String dateString = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = dateFormat.parse(dateString);
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            realEstate = REAL_ESTATE_SERVICE.getAvailableById(parseLong(realEstateString));

            meeting.setMeetingDateTime(date);
            meeting.setInquiryDate(new Date());
            meeting.setMeetingStatus("Pending");
            meeting.setBuyer(client);
            meeting.setRealEstate(realEstate);
            meeting.setEmployee(employee);

            MEETING_SERVICE.create(meeting, realEstate.getId(), client.getId(), employee.getId());

            System.out.println("\nYour meeting will be at " + date + " with " + employee.getFirstName() + " " + employee.getLastName());

        } catch (ParseException e) {
            System.out.println("Enter your date in the yyyy-MM-dd format");
        } catch (EntityNotFoundException | FieldValidationException e) {
            System.out.println(e.getMessage());
            System.out.println("Error occurred. Meeting cannot be created.");
        }
    }

    public static void viewClientsMeetings(Scanner scanner, Client client) throws EntityNotFoundException, FieldValidationException {
        List<Meeting> meetings = MEETING_SERVICE.getByClient(client);

        if (meetings.isEmpty()) {
            System.out.println("You have no meetings yet");
        } else {
            System.out.println("All your meetings");
            System.out.println(Meeting.getTableHeader());
            for (Meeting meeting : meetings) {
                System.out.println(meeting);
            }

            System.out.println("Do you want to change any of the meeting?");
            System.out.println("1. YES");
            System.out.println("2. Exit");
            System.out.print("Your choice: ");
            String whetherToChangeMeeting = scanner.nextLine();
            switch (whetherToChangeMeeting) {
                case "1":
                    System.out.println("Input the id of the meeting you want to change");
                    String meetingId = scanner.nextLine();
                    Meeting meeting = MEETING_SERVICE.getById(parseLong(meetingId));
                    System.out.println("If you need, you can change the date of the meeting, or employee\n" +
                            "1. Change date \n" +
                            "2. Change employee\n" +
                            "3. Exit");
                    String choiceMeeting = scanner.nextLine();
                    switch (choiceMeeting) {
                        case "1":
                            System.out.println("Input the new date");
                            String dateString = scanner.nextLine();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date utilDate = null;
                            try {
                                utilDate = dateFormat.parse(dateString);
                            } catch (ParseException e) {
                                System.out.println("Enter your date in the yyyy-MM-dd format");
                            }
                            java.sql.Date date = new java.sql.Date(utilDate.getTime());

                            meeting.setMeetingDateTime(date);
                            MEETING_SERVICE.update(meeting, meeting.getRealEstate().getId(),
                                    client.getId(), meeting.getEmployee().getId());
                            break;
                        case "2":
                            Employee employee = EmployeeAction.chooseEmployee(scanner);
                            meeting.setEmployee(employee);
                            MEETING_SERVICE.update(meeting, meeting.getRealEstate().getId(),
                                    client.getId(), meeting.getEmployee().getId());
                            break;
                        case "3":
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    MEETING_SERVICE.update(meeting, meeting.getRealEstate().getId(),
                            client.getId(), meeting.getEmployee().getId());
                case "2":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static long parseLong(String choice) throws FieldValidationException {
        try {
            return Long.parseLong(choice);
        } catch (NullPointerException | NumberFormatException e) {
            throw new FieldValidationException(String.format("Specified incorrect id: %s", choice), e);
        }
    }
}
