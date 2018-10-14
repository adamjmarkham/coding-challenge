package com.landbay.service;

import com.landbay.model.Loan;

public interface LoanService {

    Loan getLoan(int id);

    int createLoan(Loan testLoan);

    void deleteLoan(int id);
}
