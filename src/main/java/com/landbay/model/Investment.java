package com.landbay.model;

import java.util.Objects;

public class Investment {

    private int id;
    private long amount;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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
