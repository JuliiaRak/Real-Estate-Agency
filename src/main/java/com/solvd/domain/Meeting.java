package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Meeting {
    private long id;
    private Date meetingDateTime;
    private Date inquiryDate;
    private String meetingStatus;
    private RealEstate realEstate;
    private Client buyer;
    private Employee employee;

    public Meeting(Date meetingDateTime, Date inquiryDate, String meetingStatus, RealEstate realEstate, Client buyer, Employee employee) {
        this.meetingDateTime = meetingDateTime;
        this.inquiryDate = inquiryDate;
        this.meetingStatus = meetingStatus;
        this.realEstate = realEstate;
        this.buyer = buyer;
        this.employee = employee;
    }

    public static String getTableHeader() {
        return String.format("\033[1m| %-9s | %-12s | %-7s | %-10s | %-15s | %-28s |\033[0m",
                "MeetingID", "RealEstateID", "BayerID", "EmployeeID", "Meeting Status", "Meeting Date");
    }

    @Override
    public String toString() {
        return String.format("| %-9d | %-12s | %-7s | %-10s | %-15s | %-28s |",
                id, realEstate.getId(), buyer.getId(), employee.getId(), meetingStatus, meetingDateTime);
    }
}
