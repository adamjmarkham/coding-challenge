package com.landbay.model.internal;

import javax.persistence.*;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private long amount;
    @ManyToOne
    private Loan loan;
    private int lenderId;

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
}
