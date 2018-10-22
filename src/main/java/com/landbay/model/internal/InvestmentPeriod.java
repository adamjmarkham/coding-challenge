package com.landbay.model.internal;

import java.time.LocalDate;

public class InvestmentPeriod {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public InvestmentPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
        if (!(o instanceof InvestmentPeriod)) { return false; }

        InvestmentPeriod that = (InvestmentPeriod) o;

        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) { return false; }
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
