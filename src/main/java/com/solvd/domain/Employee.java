package com.solvd.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private Date hireDate;
    private long salary;

    public Employee(String firstName, String lastName, String email, String phoneNumber, String position, Date hireDate, long salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.hireDate = hireDate;
        this.salary = salary;
    }
}
