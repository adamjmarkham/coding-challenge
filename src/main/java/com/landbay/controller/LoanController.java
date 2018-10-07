package com.landbay.controller;

import com.landbay.model.Loan;
import com.landbay.model.PropertyAddress;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RestController
@RequestMapping(value = "/api/loan")
public class LoanController {

    @GetMapping(value = "/{loanId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Loan> getLoan(@PathVariable(value = "loanId") String loanId) {
        Loan loan = new Loan();
        loan.setId(1);
        loan.setAnnualInterest(new BigDecimal("3.4"));
        loan.setTerm(25);
        loan.setAmount(20000000);

        Date endDate = new GregorianCalendar(2043, Calendar.OCTOBER, 5).getTime();
        loan.setEndDate(endDate);

        PropertyAddress propertyAddress = new PropertyAddress();
        propertyAddress.setNumber(18);
        propertyAddress.setStreet("London Road");
        propertyAddress.setCity("London");
        propertyAddress.setPostCode("CR7 7PB");
        loan.setPropertyAddress(propertyAddress);

        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
