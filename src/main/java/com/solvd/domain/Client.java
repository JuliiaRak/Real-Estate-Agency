package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class Client {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
}
