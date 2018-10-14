package com.landbay.service;

import com.landbay.model.Loan;
import com.landbay.repository.LoanRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryBackedLoanService implements LoanService {

    private final LoanRepository loanRepository;

    public RepositoryBackedLoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan getLoan(int id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public int createLoan(Loan testLoan) {
        Loan createdLoan = loanRepository.save(testLoan);

        return createdLoan.getId();
    }

    @Override
    public void deleteLoan(int id) {
        loanRepository.deleteById(id);
    }
}
