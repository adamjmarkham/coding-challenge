package com.landbay.calculator;

import com.landbay.model.internal.InterestOwedCalculationInput;
import com.landbay.model.internal.InterestOwedResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class MonthlyInterestCalculator implements InterestCalculator {

    @Override
    public InterestOwedResult calculateInterest(List<InterestOwedCalculationInput> inputs) {
        BigDecimal totalInterestOwed = inputs.stream()
                .map(input -> {
                    long amount = input.getAmount();
                    BigDecimal decimalAnnualInterestRate = toDecimal(input.getAnnualInterestRate());
                    BigDecimal monthlyInterestRate = toMonthly(decimalAnnualInterestRate);

                    return calculateInterest(amount, monthlyInterestRate);
                })
                .reduce(BigDecimal::add)
                .get();

        return new InterestOwedResult(totalInterestOwed.longValue());
    }

    private BigDecimal calculateInterest(long amount, BigDecimal monthlyInterestRate) {
        return monthlyInterestRate.multiply(new BigDecimal(amount));
    }

    private BigDecimal toMonthly(BigDecimal annualInterestRate) {
        return annualInterestRate.divide(new BigDecimal(12), 4, RoundingMode.HALF_EVEN);
    }

    private BigDecimal toDecimal(BigDecimal percentage) {
        return percentage.divide(new BigDecimal(100), 4, RoundingMode.HALF_EVEN);
    }
}
