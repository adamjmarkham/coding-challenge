package com.landbay.e2e;

import com.landbay.model.Loan;
import com.landbay.testdata.LoanTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanServiceEndToEndTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canCreateThenGetLoan() {
        Loan testLoan = LoanTestData.testLoanWithoutId();
        Loan loan = restTemplate.postForObject("/api/loan", testLoan, Loan.class);

        Loan retrievedLoan = restTemplate.getForObject("/api/loan/" + loan.getId(), Loan.class);

        assertThat(retrievedLoan.getId(), is(notNullValue()));
        assertThat(retrievedLoan.getAmount(), is(testLoan.getAmount()));
        assertThat(retrievedLoan.getAnnualInterest(), is(testLoan.getAnnualInterest()));
        assertThat(retrievedLoan.getEndDate(), is(testLoan.getEndDate()));
        assertThat(retrievedLoan.getPropertyAddress(), is(testLoan.getPropertyAddress()));
        assertThat(retrievedLoan.getTerm(), is(testLoan.getTerm()));
    }
}
