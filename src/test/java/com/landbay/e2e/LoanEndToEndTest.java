package com.landbay.e2e;

import com.landbay.model.dto.InvestableLoanDTO;
import com.landbay.model.dto.InvestmentDTO;
import com.landbay.model.internal.Investment;
import com.landbay.model.internal.Loan;
import com.landbay.model.rest.InvestmentCreateRequest;
import com.landbay.repository.LoanRepository;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static com.landbay.testdata.LoanTestData.testLoanWithoutId;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanEndToEndTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanRepository investmentRepository;

    @After
    public void cleanup() {
        investmentRepository.deleteAll();
        loanRepository.deleteAll();
    }

    @Test
    public void canCreateThenGetLoan() {
        Loan testLoan = testLoanWithoutId();
        Loan loan = createLoan(testLoan);

        Loan retrievedLoan = getLoan(loan);

        assertThat(retrievedLoan.getId(), is(notNullValue()));
        assertThat(retrievedLoan.getAmount(), is(testLoan.getAmount()));
        assertThat(retrievedLoan.getAnnualInterest(), is(testLoan.getAnnualInterest()));
        assertThat(retrievedLoan.getEndDate(), is(testLoan.getEndDate()));
        assertThat(retrievedLoan.getPropertyAddress(), is(testLoan.getPropertyAddress()));
        assertThat(retrievedLoan.getTerm(), is(testLoan.getTerm()));
    }

    @Test
    public void canDeleteALoan() {
        Loan testLoan = testLoanWithoutId();
        Loan loan = createLoan(testLoan);

        restTemplate.delete("/api/loan/" + loan.getId());

        Loan retrievedLoan = getLoan(loan);

        assertThat(retrievedLoan, is(nullValue()));
    }

    @Test
    public void canInvestIntoLoanThenGetInvestments() {
        Loan loan = createLoan(testLoanWithoutId());

        Investment testInvestment = new Investment();
        testInvestment.setAmount(1000);
        testInvestment.setLoan(loan);

        InvestmentCreateRequest investmentCreateRequest = new InvestmentCreateRequest();
        investmentCreateRequest.setAmount(1000);
        investmentCreateRequest.setLoanId(loan.getId());

        InvestmentDTO investment = restTemplate.postForObject("/api/investment", investmentCreateRequest,
                InvestmentDTO.class);

        InvestableLoanDTO investableLoan = getLoanDTO(loan.getId());

        assertThat(investableLoan.getInvestments(), contains(investmentAmount(1000)));
        assertThat(investableLoan.getInvestments(), contains(investmentInto(loan)));
    }

    private Matcher<InvestmentDTO> investmentInto(Loan loan) {
        return new FeatureMatcher<InvestmentDTO, Integer>(equalTo(loan.getId()), "investment loan ", "investment loan" +
                " ") {
            @Override
            protected Integer featureValueOf(InvestmentDTO investment) {
                return investment.getLoan().getId();
            }
        };
    }

    private Matcher<InvestmentDTO> investmentAmount(long amount) {
        return new FeatureMatcher<InvestmentDTO, Long>(equalTo(amount), "investment amount ", "investment amount ") {
            @Override
            protected Long featureValueOf(InvestmentDTO investment) {
                return investment.getAmount();
            }
        };
    }

    private Loan getLoan(Loan loan) {
        return restTemplate.getForObject("/api/loan/" + loan.getId(), Loan.class);
    }

    private InvestableLoanDTO getLoanDTO(int loanId) {
        return restTemplate.getForObject("/api/loan/" + loanId, InvestableLoanDTO.class);
    }

    private Loan createLoan(Loan testLoan) {
        return restTemplate.postForObject("/api/loan", testLoan, Loan.class);
    }
}
