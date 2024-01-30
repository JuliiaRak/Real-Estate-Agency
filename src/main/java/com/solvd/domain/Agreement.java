package com.solvd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    private long id;
    private Date date;
    private BigDecimal amount;
    private String duration;
    private String status;
    private RealEstate realEstate;
    private Client client;

    public static String getTableHeader() {
        return String.format("\033[1m| %-11s | %-10s | %-12s | %-8s | %-28s |\033[0m",
                "AgreementID", "Amount", "RealEstateID", "ClientID", "Date of creation");
    }

    @Override
    public String toString() {
        return String.format("| %-11d | %-10s | %-12s | %-8s | %-28s |",
                id, amount, realEstate.getId(), client.getId(), date);
    }
}
