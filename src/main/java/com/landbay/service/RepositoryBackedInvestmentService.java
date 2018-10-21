package com.landbay.service;

import com.landbay.model.internal.Investment;
import com.landbay.model.internal.Loan;
import com.landbay.repository.InvestmentRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryBackedInvestmentService implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    public RepositoryBackedInvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public Investment createInvestment(long amount, Loan loan) {
        Investment investment = new Investment();
        investment.setAmount(amount);
        investment.setLoan(loan);

        Investment createdInvestment = investmentRepository.save(investment);

        return createdInvestment;
    }
}
