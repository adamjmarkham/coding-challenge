package com.landbay.service;

import com.landbay.calculator.InterestCalculator;
import com.landbay.model.internal.InterestOwedCalculationInput;
import com.landbay.model.internal.InterestOwedResult;
import com.landbay.model.internal.Investment;
import com.landbay.model.internal.Loan;
import com.landbay.repository.InvestmentRepository;
import org.springframework.stereotype.Component;

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
    public Investment createInvestment(long amount, Loan loan, int lenderId) {
        Investment investment = new Investment();
        investment.setAmount(amount);
        investment.setLoan(loan);
        investment.setLenderId(lenderId);

        return investmentRepository.save(investment);
    }

    @Override
    public InterestOwedResult calculateInterestOwed(int lenderId, long period) {
        Set<Investment> investments = investmentRepository.findByLenderId(lenderId);

        List<InterestOwedCalculationInput> interestOwedInputs = investments.stream()
                .map(investment -> {
                    InterestOwedCalculationInput interestOwedCalculationInput = new InterestOwedCalculationInput();
                    interestOwedCalculationInput.setAmount(investment.getAmount());
                    Loan loan = investment.getLoan();
                    interestOwedCalculationInput.setAnnualInterestRate(loan.getAnnualInterest());
                    return interestOwedCalculationInput;
                })
                .collect(toList());

        return interestCalculator.calculateInterest(interestOwedInputs, period);
    }
}
