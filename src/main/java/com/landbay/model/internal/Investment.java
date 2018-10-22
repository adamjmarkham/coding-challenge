package com.landbay.model.internal;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private long amount;
    @ManyToOne
    private Loan loan;
    private int lenderId;
    private LocalDate startDate;
    private LocalDate endDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setLoan(Loan loan) {
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

    public Loan getLoan() {
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
}
