package com.solvd.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class Client {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date registrationDate;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
