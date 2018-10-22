package com.landbay.testdata;

import com.landbay.model.internal.InterestOwedResult;
import com.landbay.model.internal.Investment;
import com.landbay.model.rest.InvestmentCreateRequest;

public class InvestmentTestData {

    public static InvestmentCreateRequest testInvestmentCreateRequest() {
        InvestmentCreateRequest investmentCreateRequest = new InvestmentCreateRequest();
        investmentCreateRequest.setLoanId(1);
        investmentCreateRequest.setAmount(1000);
        return investmentCreateRequest;
    }

    public static Investment testInvestment() {
        Investment investment = new Investment();
        investment.setId(1);
        investment.setAmount(1000);
        investment.setLoan(LoanTestData.getTestLoan());
        investment.setLenderId(1);
        return investment;
    }

    public static InterestOwedResult interestOwedResult(long total) {
        return new InterestOwedResult(total);
    }
}
