package com.cpf.broker.resource;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageView {

    private final BigDecimal percentage;

    public PercentageView(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String render() {
        return percentage.setScale(2, RoundingMode.HALF_EVEN).toPlainString();
    }
}
