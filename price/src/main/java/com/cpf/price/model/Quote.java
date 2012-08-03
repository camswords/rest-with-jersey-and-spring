package com.cpf.price.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Quote {

    private final BigDecimal premium;
    private final BigDecimal interestRate;
    private final String id;

    public Quote(BigDecimal premium, BigDecimal interestRate) {
        this.id = UUID.randomUUID().toString();
        this.premium = premium;
        this.interestRate = interestRate;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public BigDecimal getInterest() {
        return premium.multiply(interestRate).multiply(new BigDecimal("0.01"));
    }
}
