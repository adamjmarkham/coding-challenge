package com.landbay.service;

import com.landbay.calculator.InterestCalculator;
import com.landbay.model.internal.*;
import com.landbay.model.rest.InvestmentCreateRequest;
import com.landbay.repository.InvestmentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class RepositoryBackedInvestmentService implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final InterestCalculator interestCalculator;

    public RepositoryBackedInvestmentService(InvestmentRepository investmentRepository,
                                             InterestCalculator interestCalculator) {
        this.investmentRepository = investmentRepository;
        this.interestCalculator = interestCalculator;
    }

    @Override
    public Investment createInvestment(InvestmentCreateRequest investmentCreateRequest, Loan loan) {
        Investment investment = new Investment();
        investment.setAmount(investmentCreateRequest.getAmount());
        investment.setLoan(loan);
        investment.setLenderId(investmentCreateRequest.getLenderId());
        investment.setStartDate(investmentCreateRequest.getStartDate());
        investment.setEndDate(investmentCreateRequest.getEndDate());

        return investmentRepository.save(investment);
    }

    @Override
    public InterestOwedResult calculateInterestOwed(int lenderId, InvestmentPeriod investmentPeriod) {
        Set<Investment> investments = investmentRepository.findByLenderId(lenderId);

        LocalDate startDate = investmentPeriod.getStartDate();
        LocalDate endDate = investmentPeriod.getEndDate();
        List<InterestOwedCalculationInput> interestOwedInputs = investments.stream()
                .filter(investment -> withinStartDate(startDate, investment) && withinEndDate(endDate, investment))
                .map(investment -> {
                    InterestOwedCalculationInput interestOwedCalculationInput = new InterestOwedCalculationInput();
                    interestOwedCalculationInput.setAmount(investment.getAmount());
                    Loan loan = investment.getLoan();
                    interestOwedCalculationInput.setAnnualInterestRate(loan.getAnnualInterest());
                    return interestOwedCalculationInput;
                })
                .collect(toList());

        return interestCalculator.calculateInterest(interestOwedInputs);
    }

    private boolean withinStartDate(LocalDate startDate, Investment investment) {
        LocalDate investmentStartDate = investment.getStartDate();
        return investmentStartDate.isAfter(startDate) || investmentStartDate.isEqual(startDate);
    }

    private boolean withinEndDate(LocalDate endDate, Investment investment) {
        LocalDate investmentEndDate = investment.getEndDate();
        return investmentEndDate.isBefore(endDate) || investmentEndDate.isEqual(endDate);
    }
}
