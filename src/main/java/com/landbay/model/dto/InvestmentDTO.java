package com.landbay.model.dto;

import java.time.LocalDate;

public class InvestmentDTO {

    private int id;
    private long amount;
    private LoanDTO loan;
    private int lenderId;
    private LocalDate startDate;
    private LocalDate endDate;

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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof InvestmentDTO)) { return false; }

        InvestmentDTO that = (InvestmentDTO) o;

        if (id != that.id) { return false; }
        if (amount != that.amount) { return false; }
        if (lenderId != that.lenderId) { return false; }
        if (loan != null ? !loan.equals(that.loan) : that.loan != null) { return false; }
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) { return false; }
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (loan != null ? loan.hashCode() : 0);
        result = 31 * result + lenderId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
