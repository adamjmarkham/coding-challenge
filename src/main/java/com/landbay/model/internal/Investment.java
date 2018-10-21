package com.landbay.model.internal;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private long amount;
    @ManyToOne
    private Loan loan;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Investment)) { return false; }
        Investment that = (Investment) o;
        return id == that.id &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
