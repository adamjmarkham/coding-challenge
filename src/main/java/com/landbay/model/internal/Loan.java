package com.landbay.model.internal;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private long amount;
    private BigDecimal annualInterest;
    @Embedded
    private PropertyAddress propertyAddress;
    private int term;
    @OneToMany(mappedBy = "loan", orphanRemoval = true)
    private Set<Investment> investments;
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

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<Investment> investments) {
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
        if (!(o instanceof Loan)) { return false; }

        Loan loan = (Loan) o;

        if (amount != loan.amount) { return false; }
        if (term != loan.term) { return false; }
        if (id != null ? !id.equals(loan.id) : loan.id != null) { return false; }
        if (annualInterest != null ? !annualInterest.equals(loan.annualInterest) : loan.annualInterest != null) {
            return false;
        }
        if (propertyAddress != null ? !propertyAddress.equals(loan.propertyAddress) : loan.propertyAddress != null) {
            return false;
        }
        if (investments != null ? !investments.equals(loan.investments) : loan.investments != null) { return false; }
        return endDate != null ? endDate.equals(loan.endDate) : loan.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (annualInterest != null ? annualInterest.hashCode() : 0);
        result = 31 * result + (propertyAddress != null ? propertyAddress.hashCode() : 0);
        result = 31 * result + term;
        result = 31 * result + (investments != null ? investments.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
