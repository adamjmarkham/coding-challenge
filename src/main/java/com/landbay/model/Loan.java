package com.landbay.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class Loan {

    private int id;
    private long amount;
    private BigDecimal annualInterest;
    private PropertyAddress propertyAddress;
    private int term;
    private Set<Investment> investments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public BigDecimal getAnnualInterest() {
        return annualInterest;
    }

    public void setAnnualInterest(BigDecimal annualInterest) {
        this.annualInterest = annualInterest;
    }

    public PropertyAddress getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(PropertyAddress propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<Investment> investments) {
        this.investments = investments;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
