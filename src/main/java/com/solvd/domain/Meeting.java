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
}
