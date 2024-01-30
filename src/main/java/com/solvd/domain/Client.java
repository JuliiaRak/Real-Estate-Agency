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

    public static Builder builder() {
        return new Builder();
    }

    public static String getTableHeader() {
        return String.format("\033[1m| %-8s | %-10s | %-10s |\033[0m",
                "ClientID", "First name", "Last name");
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-10s | %-10s |",
                id, firstName, lastName);
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Date registrationDate;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Client build() {
            Client client = new Client();

            client.id = this.id;
            client.firstName = this.firstName;
            client.lastName = this.lastName;
            client.email = this.email;
            client.phoneNumber = this.phoneNumber;
            client.registrationDate = this.registrationDate;
            return client;
        }
    }
}
