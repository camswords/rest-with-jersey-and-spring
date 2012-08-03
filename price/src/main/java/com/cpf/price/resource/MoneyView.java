package com.cpf.price.resource;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyView {

    private final BigDecimal amount;

    public MoneyView(BigDecimal amount) {
        this.amount = amount;
    }

    public String render() {
        return amount.setScale(2, RoundingMode.HALF_EVEN).toPlainString();
    }
}
