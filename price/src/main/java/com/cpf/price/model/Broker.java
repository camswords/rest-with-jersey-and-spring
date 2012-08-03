package com.cpf.price.model;

import java.math.BigDecimal;

public class Broker {

    private final String id;
    private final BigDecimal interestRate;

    public Broker(String id, BigDecimal interestRate) {
        this.id = id;
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }
}
