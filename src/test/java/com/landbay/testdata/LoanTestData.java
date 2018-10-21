package com.landbay.testdata;

import com.landbay.model.internal.Loan;
import com.landbay.model.internal.PropertyAddress;

import java.math.BigDecimal;

public class LoanTestData {

    public static Loan getTestLoan() {
        Loan loan = new Loan();
        loan.setId(1);
        loan.setAnnualInterest(new BigDecimal("3.40"));
        loan.setTerm(25);
        loan.setAmount(20000000);
        loan.setEndDate("2043-10-04");

        PropertyAddress propertyAddress = new PropertyAddress();
        propertyAddress.setNumber(18);
        propertyAddress.setStreet("London Road");
        propertyAddress.setCity("London");
        propertyAddress.setPostCode("CR7 7PB");
        loan.setPropertyAddress(propertyAddress);

        return loan;
    }

    public static Loan testLoanWithoutId() {
        Loan testLoan = LoanTestData.getTestLoan();
        testLoan.setId(null);
        return testLoan;
    }
}
