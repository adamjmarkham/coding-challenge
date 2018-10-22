package com.landbay.model.rest;

import java.util.Objects;

public class InvestmentCreateRequest {

    private long amount;
    private int loanId;
    private int lenderId;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof InvestmentCreateRequest)) { return false; }
        InvestmentCreateRequest that = (InvestmentCreateRequest) o;
        return amount == that.amount &&
                loanId == that.loanId &&
                lenderId == that.lenderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, loanId, lenderId);
    }
}
