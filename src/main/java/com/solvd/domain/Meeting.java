package com.solvd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
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
}
