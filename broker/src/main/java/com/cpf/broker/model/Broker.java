package com.cpf.broker.model;

import java.math.BigDecimal;

public class Broker {

    private final String id;

    public Broker(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getInterestRate() {
        return new BigDecimal("3.15");
    }
}
