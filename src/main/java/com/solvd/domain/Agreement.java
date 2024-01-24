package com.solvd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
public class Agreement {
    private long id;
    private Date date;
    private BigDecimal amount;
    private String duration;
    private String status;
    private RealEstate realEstate;
    private Client client;
}
