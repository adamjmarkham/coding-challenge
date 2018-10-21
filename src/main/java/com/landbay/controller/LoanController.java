package com.landbay.controller;

import com.landbay.model.dto.InvestableLoanDTO;
import com.landbay.model.dto.LoanDTO;
import com.landbay.model.internal.Loan;
import com.landbay.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/loan")
public class LoanController {

    private final LoanService loanService;
    private final ModelMapper modelMapper;

    public LoanController(LoanService loanService, ModelMapper modelMapper) {
        this.loanService = loanService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<InvestableLoanDTO> getLoan(@PathVariable(value = "loanId") int loanId) {
        Loan loan = loanService.getLoan(loanId);

        return new ResponseEntity<>(convertToInvestableDTO(loan), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody Loan loan) {
        int id = loanService.createLoan(loan);

        loan.setId(id);

        return new ResponseEntity<>(convertToDTO(loan), HttpStatus.OK);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Loan> deleteLoan(@PathVariable(value = "loanId") int loanId) {
        loanService.deleteLoan(loanId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private LoanDTO convertToDTO(Loan loan) {
        return modelMapper.map(loan, LoanDTO.class);
    }

    private InvestableLoanDTO convertToInvestableDTO(Loan loan) {
        if (loan == null) {
            return null;
        }

        return modelMapper.map(loan, InvestableLoanDTO.class);
    }
}
