package com.landbay.controller;

import com.landbay.model.dto.InvestmentDTO;
import com.landbay.model.internal.Investment;
import com.landbay.model.internal.Loan;
import com.landbay.model.rest.InvestmentCreateRequest;
import com.landbay.service.InvestmentService;
import com.landbay.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Loan loan = loanService.getLoan(investmentCreateRequest.getLoanId());

        Investment investment = investmentService.createInvestment(amount, loan);

        return new ResponseEntity<>(convertToDTO(investment), HttpStatus.OK);
    }

    private InvestmentDTO convertToDTO(Investment investment) {
        return modelMapper.map(investment, InvestmentDTO.class);
    }
}
