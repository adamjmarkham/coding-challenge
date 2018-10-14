package com.landbay.e2e;

import com.landbay.model.Loan;
import com.landbay.repository.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static com.landbay.testdata.LoanTestData.testLoanWithoutId;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanServiceEndToEndTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoanRepository loanRepository;

    @Before
    public void setup() {
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

        restTemplate.delete("/api/loan/1");

        Loan retrievedLoan = getLoan(loan);

        assertThat(retrievedLoan, is(nullValue()));
    }

    private Loan getLoan(Loan loan) {
        return restTemplate.getForObject("/api/loan/" + loan.getId(), Loan.class);
    }

    private Loan createLoan(Loan testLoan) {
        return restTemplate.postForObject("/api/loan", testLoan, Loan.class);
    }
}
