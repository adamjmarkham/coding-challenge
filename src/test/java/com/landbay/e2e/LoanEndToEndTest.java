package com.landbay.e2e;

import com.landbay.model.dto.InvestableLoanDTO;
import com.landbay.model.dto.InvestmentDTO;
import com.landbay.model.internal.Loan;
import com.landbay.model.rest.InvestmentCreateRequest;
import com.landbay.repository.InvestmentRepository;
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
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanEndToEndTest {

    private static final int LENDER_ID = 1;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

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

        InvestmentCreateRequest investmentCreateRequestOne = investmentCreateRequest(loan, 1000);
        InvestmentCreateRequest investmentCreateRequestTwo = investmentCreateRequest(loan, 2000);

        createInvestment(investmentCreateRequestOne);
        createInvestment(investmentCreateRequestTwo);

        InvestableLoanDTO investableLoan = getLoanDTO(loan.getId());

        assertThat(investableLoan.getInvestments(), hasItem(investmentAmount(1000)));
        assertThat(investableLoan.getInvestments(), hasItem(investmentAmount(2000)));
        assertThat(investableLoan.getInvestments(), hasSize(2));
        assertThat(investableLoan.getInvestments(), hasItem(investmentInto(loan)));
        assertThat(investableLoan.getInvestments(), hasItem(investmentBy(LENDER_ID)));
    }

    private InvestmentCreateRequest investmentCreateRequest(Loan loan, int amount) {
        InvestmentCreateRequest investmentCreateRequest = new InvestmentCreateRequest();
        investmentCreateRequest.setAmount(amount);
        investmentCreateRequest.setLoanId(loan.getId());
        investmentCreateRequest.setLenderId(LENDER_ID);
        return investmentCreateRequest;
    }

    private void createInvestment(InvestmentCreateRequest investmentCreateRequest) {
        restTemplate.postForObject("/api/investment", investmentCreateRequest,
                InvestmentDTO.class
        );
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

    private Matcher<InvestmentDTO> investmentBy(int lenderId) {
        return new FeatureMatcher<InvestmentDTO, Integer>(equalTo(lenderId), "investment by lender ", "investment by " +
                "lender ") {
            @Override
            protected Integer featureValueOf(InvestmentDTO investment) {
                return investment.getLenderId();
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
