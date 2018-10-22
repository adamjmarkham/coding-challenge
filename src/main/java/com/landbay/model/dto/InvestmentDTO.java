package com.landbay.model.dto;

import java.util.Objects;

public class InvestmentDTO {

    private int id;
    private long amount;
    private LoanDTO loan;
    private int lenderId;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setLoan(LoanDTO loan) {
        this.loan = loan;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
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

    public int getLenderId() {
        return lenderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof InvestmentDTO)) { return false; }
        InvestmentDTO that = (InvestmentDTO) o;
        return id == that.id &&
                amount == that.amount &&
                lenderId == that.lenderId &&
                Objects.equals(loan, that.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, loan, lenderId);
    }
}
