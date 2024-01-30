package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private int salary;

    public Employee(String firstName, String lastName, String email, String phoneNumber, String position, Date hireDate, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public static String getTableHeader() {
        return String.format("\033[1m| %-10s | %-10s | %-10s | %-11s |\033[0m",
                "EmployeeID", "First name", "Last name", "Position");
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-10s | %-10s | %-11s |",
                id, firstName, lastName, position);
    }
}
