package com.landbay.model.dto;

import java.util.Objects;

public class InvestmentDTO {

    private int id;
    private long amount;
    private LoanDTO loan;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setLoan(LoanDTO loan) {
        this.loan = loan;
    }

    public int getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public LoanDTO getLoan() {
        return loan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof InvestmentDTO)) { return false; }
        InvestmentDTO that = (InvestmentDTO) o;
        return id == that.id &&
                amount == that.amount &&
                Objects.equals(loan, that.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, loan);
    }
}
