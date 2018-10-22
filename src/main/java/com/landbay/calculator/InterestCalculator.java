package com.landbay.calculator;

import com.landbay.model.internal.InterestOwedCalculationInput;
import com.landbay.model.internal.InterestOwedResult;

import java.util.List;

public interface InterestCalculator {

    InterestOwedResult calculateInterest(List<InterestOwedCalculationInput> inputs);
}
