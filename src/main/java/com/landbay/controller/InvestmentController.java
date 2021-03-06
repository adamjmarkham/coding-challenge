package com.landbay.controller;

import com.landbay.model.dto.InvestmentDTO;
import com.landbay.model.internal.InterestOwedResult;
import com.landbay.model.internal.Investment;
import com.landbay.model.internal.Loan;
import com.landbay.model.rest.InvestmentCreateRequest;
import com.landbay.service.InvestmentService;
import com.landbay.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping(value = "/api/investment")
public class InvestmentController {

    private final InvestmentService investmentService;
    private final LoanService loanService;
    private final ModelMapper modelMapper = new ModelMapper();

    public InvestmentController(InvestmentService investmentService, LoanService loanService) {
        this.investmentService = investmentService;
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<InvestmentDTO> createInvestment(@RequestBody InvestmentCreateRequest investmentCreateRequest) {
        long amount = investmentCreateRequest.getAmount();
        int lenderId = investmentCreateRequest.getLenderId();
        Loan loan = loanService.getLoan(investmentCreateRequest.getLoanId());

        Investment investment = investmentService.createInvestment(amount, loan, lenderId);

        return new ResponseEntity<>(convertToDTO(investment), HttpStatus.OK);
    }

    @GetMapping("/interest/{lenderId}")
    public ResponseEntity<InterestOwedResult> interestOwed(@PathVariable(value = "lenderId") int lenderId,
                                                           @RequestParam(value = "start") @DateTimeFormat(pattern =
                                                                   "yyyy-MM-dd") LocalDate start,
                                                           @RequestParam(value = "end") @DateTimeFormat(pattern =
                                                                   "yyyy-MM-dd") LocalDate end) {
        long period = DAYS.between(start, end);

        InterestOwedResult interestOwedResult = investmentService.calculateInterestOwed(lenderId, period);

        return new ResponseEntity<>(interestOwedResult, HttpStatus.OK);
    }

    private InvestmentDTO convertToDTO(Investment investment) {
        return modelMapper.map(investment, InvestmentDTO.class);
    }
}
