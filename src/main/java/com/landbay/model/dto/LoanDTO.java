package com.landbay.model.dto;

import com.landbay.model.internal.PropertyAddress;

import java.math.BigDecimal;
import java.util.Objects;

public class LoanDTO {

    private Integer id;
    private long amount;
    private BigDecimal annualInterest;
    private PropertyAddress propertyAddress;
    private int term;
    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof LoanDTO)) { return false; }
        LoanDTO loanDTO = (LoanDTO) o;
        return amount == loanDTO.amount &&
                term == loanDTO.term &&
                Objects.equals(id, loanDTO.id) &&
                Objects.equals(annualInterest, loanDTO.annualInterest) &&
                Objects.equals(propertyAddress, loanDTO.propertyAddress) &&
                Objects.equals(endDate, loanDTO.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, annualInterest, propertyAddress, term, endDate);
    }
}
