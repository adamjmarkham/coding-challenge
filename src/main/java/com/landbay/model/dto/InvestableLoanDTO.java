package com.landbay.model.dto;

import com.landbay.model.internal.PropertyAddress;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class InvestableLoanDTO {

    private Integer id;
    private long amount;
    private BigDecimal annualInterest;
    private PropertyAddress propertyAddress;
    private int term;
    private Set<InvestmentDTO> investments;
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

    public Set<InvestmentDTO> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<InvestmentDTO> investments) {
        this.investments = investments;
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
        if (!(o instanceof InvestableLoanDTO)) { return false; }
        InvestableLoanDTO loan = (InvestableLoanDTO) o;
        return amount == loan.amount &&
                term == loan.term &&
                Objects.equals(id, loan.id) &&
                Objects.equals(annualInterest, loan.annualInterest) &&
                Objects.equals(propertyAddress, loan.propertyAddress) &&
                Objects.equals(investments, loan.investments) &&
                Objects.equals(endDate, loan.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, annualInterest, propertyAddress, term, investments, endDate);
    }
}
