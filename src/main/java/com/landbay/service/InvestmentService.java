package com.landbay.service;

import com.landbay.model.internal.InterestOwedResult;
import com.landbay.model.internal.Investment;
import com.landbay.model.internal.Loan;

public interface InvestmentService {

    Investment createInvestment(long amount, Loan loan, int lenderId);

    InterestOwedResult calculateInterestOwed(int lenderId);
}
