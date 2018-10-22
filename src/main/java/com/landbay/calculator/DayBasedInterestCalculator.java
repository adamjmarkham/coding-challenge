package com.landbay.calculator;

import com.landbay.model.internal.InterestOwedCalculationInput;
import com.landbay.model.internal.InterestOwedResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class DayBasedInterestCalculator implements InterestCalculator {

    @Override
    public InterestOwedResult calculateInterest(List<InterestOwedCalculationInput> inputs, long period) {
        BigDecimal totalInterestOwed = inputs.stream()
                .map(input -> {
                    long amount = input.getAmount();
                    BigDecimal decimalAnnualInterestRate = toDecimal(input.getAnnualInterestRate());
                    BigDecimal monthlyInterestRate = toDailyInterestRate(decimalAnnualInterestRate);

                    return calculateInterest(amount, monthlyInterestRate).multiply(new BigDecimal(period));
                })
                .reduce(BigDecimal::add)
                .get();

        return new InterestOwedResult(totalInterestOwed.longValue());
    }

    private BigDecimal calculateInterest(long amount, BigDecimal dailyInterestRate) {
        return dailyInterestRate.multiply(new BigDecimal(amount));
    }

    private BigDecimal toDailyInterestRate(BigDecimal annualInterestRate) {
        return annualInterestRate.divide(new BigDecimal(365), 4, RoundingMode.HALF_EVEN);
    }

    private BigDecimal toDecimal(BigDecimal percentage) {
        return percentage.divide(new BigDecimal(100), 4, RoundingMode.HALF_EVEN);
    }
}
