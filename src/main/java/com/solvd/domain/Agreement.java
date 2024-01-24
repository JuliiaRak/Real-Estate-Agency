package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(of = "id")
public class Agreement {
    private long id;
    private Date date;
    private double amount;
    private String duration;
    private String status;
    private RealEstate realEstate;
    private Client client;
}
