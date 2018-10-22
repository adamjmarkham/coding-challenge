package com.landbay.model.internal;

import java.math.BigDecimal;

public class InterestOwedCalculationInput {

    private BigDecimal annualInterestRate;
    private long amount;

    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
