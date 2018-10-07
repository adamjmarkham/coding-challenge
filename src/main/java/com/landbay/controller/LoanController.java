package com.landbay.controller;

import com.landbay.model.Loan;
import com.landbay.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping(value = "/{loanId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Loan> getLoan(@PathVariable(value = "loanId") int loanId) {
        Loan loan = loanService.getLoan(loanId);

        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
