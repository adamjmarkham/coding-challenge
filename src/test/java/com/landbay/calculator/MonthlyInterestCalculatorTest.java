package com.landbay.calculator;

import com.landbay.model.internal.InterestOwedCalculationInput;
import com.landbay.model.internal.InterestOwedResult;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MonthlyInterestCalculatorTest {

    private final MonthlyInterestCalculator calculator = new MonthlyInterestCalculator();

    @Test
    public void canCalculateMonthlyInterestOwedOnMultipleInvestments() {
        List<InterestOwedCalculationInput> inputs = new ArrayList<>();

        int investmentAmount = 1000;
        String annualInterestRate = "10";
        inputs.add(interestCalculationInput(investmentAmount, annualInterestRate));
        inputs.add(interestCalculationInput(investmentAmount, annualInterestRate));

        InterestOwedResult interestOwed = calculator.calculateInterest(inputs);

        assertThat(interestOwed.getTotal(), equalTo(16L));
    }

    @Test
    public void canCalculateNonWholeInterestRates() {
        List<InterestOwedCalculationInput> inputs = new ArrayList<>();

        int investmentAmount = 1000;
        String annualInterestRate = "3.4";
        inputs.add(interestCalculationInput(investmentAmount, annualInterestRate));

        InterestOwedResult interestOwed = calculator.calculateInterest(inputs);

        assertThat(interestOwed.getTotal(), equalTo(2L));
    }

    private InterestOwedCalculationInput interestCalculationInput(int amount, String interestRate) {
        InterestOwedCalculationInput input = new InterestOwedCalculationInput();
        input.setAmount(amount);
        input.setAnnualInterestRate(new BigDecimal(interestRate));
        return input;
    }
}
