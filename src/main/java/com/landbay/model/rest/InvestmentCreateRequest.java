package com.landbay.model.rest;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InvestmentCreateRequest {

    private long amount;
    private int loanId;
    private int lenderId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof InvestmentCreateRequest)) { return false; }

        InvestmentCreateRequest that = (InvestmentCreateRequest) o;

        if (amount != that.amount) { return false; }
        if (loanId != that.loanId) { return false; }
        if (lenderId != that.lenderId) { return false; }
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) { return false; }
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (amount ^ (amount >>> 32));
        result = 31 * result + loanId;
        result = 31 * result + lenderId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
