package com.landbay.calculator;

import com.landbay.model.internal.InterestOwedCalculationInput;
import com.landbay.model.internal.InterestOwedResult;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DayBasedInterestCalculatorTest {

    private final DayBasedInterestCalculator calculator = new DayBasedInterestCalculator();
    private int period = 30;

    @Test
    public void canCalculateInterestOwedOnMultipleInvestments() {
        List<InterestOwedCalculationInput> inputs = new ArrayList<>();

        int investmentAmount = 1000;
        String annualInterestRate = "10";
        inputs.add(interestCalculationInput(investmentAmount, annualInterestRate));
        inputs.add(interestCalculationInput(investmentAmount, annualInterestRate));

        InterestOwedResult interestOwed = calculator.calculateInterest(inputs, period);

        assertThat(interestOwed.getTotal(), equalTo(18L));
    }

    @Test
    public void canCalculateNonWholeInterestRates() {
        List<InterestOwedCalculationInput> inputs = new ArrayList<>();

        int investmentAmount = 1000;
        String annualInterestRate = "3.4";
        inputs.add(interestCalculationInput(investmentAmount, annualInterestRate));

        InterestOwedResult interestOwed = calculator.calculateInterest(inputs, period);

        assertThat(interestOwed.getTotal(), equalTo(3L));
    }

    private InterestOwedCalculationInput interestCalculationInput(int amount, String interestRate) {
        InterestOwedCalculationInput input = new InterestOwedCalculationInput();
        input.setAmount(amount);
        input.setAnnualInterestRate(new BigDecimal(interestRate));
        return input;
    }
}
