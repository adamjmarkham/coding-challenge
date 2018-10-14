package com.landbay.controller;

import com.landbay.model.Loan;
import com.landbay.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable(value = "loanId") int loanId) {
        Loan loan = loanService.getLoan(loanId);

        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        int id = loanService.createLoan(loan);

        loan.setId(id);

        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Loan> deleteLoan(@PathVariable(value = "loanId") int loanId) {
        loanService.deleteLoan(loanId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
