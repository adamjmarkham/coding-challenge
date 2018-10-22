package com.landbay.service;

import com.landbay.model.internal.InterestOwedResult;
import com.landbay.model.internal.Investment;
import com.landbay.model.internal.InvestmentPeriod;
import com.landbay.model.internal.Loan;
import com.landbay.model.rest.InvestmentCreateRequest;

public interface InvestmentService {

    Investment createInvestment(InvestmentCreateRequest investmentCreateRequest, Loan loan);

    InterestOwedResult calculateInterestOwed(int lenderId, InvestmentPeriod investmentPeriod);
}
