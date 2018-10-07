package com.landbay.controller;

import com.landbay.model.Loan;
import com.landbay.model.PropertyAddress;
import com.landbay.service.LoanService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Before
    public void setup() {
        Loan loan = getTestLoan();

        given(loanService.getLoan(1)).willReturn(loan);
    }

    @Test
    public void getALoanReturns200() throws Exception {
        mockMvc.perform(get("/api/loan/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getALoanReturnsContentType() throws Exception {
        mockMvc.perform(get("/api/loan/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void getALoanReturnsJson() throws Exception {
        mockMvc.perform(get("/api/loan/1"))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("amount", is(20000000)))
                .andExpect(jsonPath("annualInterest", is(3.4)))
                .andExpect(jsonPath("term", is(25)))
                .andExpect(jsonPath("endDate", is("2043-10-04")))
                .andExpect(jsonPath("propertyAddress.number", is(18)))
                .andExpect(jsonPath("propertyAddress.street", is("London Road")))
                .andExpect(jsonPath("propertyAddress.city", is("London")))
                .andExpect(jsonPath("propertyAddress.postCode", is("CR7 7PB")));
    }

    private Loan getTestLoan() {
        Loan loan = new Loan();
        loan.setId(1);
        loan.setAnnualInterest(new BigDecimal("3.4"));
        loan.setTerm(25);
        loan.setAmount(20000000);

        Date endDate = new GregorianCalendar(2043, Calendar.OCTOBER, 5).getTime();
        loan.setEndDate(endDate);

        PropertyAddress propertyAddress = new PropertyAddress();
        propertyAddress.setNumber(18);
        propertyAddress.setStreet("London Road");
        propertyAddress.setCity("London");
        propertyAddress.setPostCode("CR7 7PB");
        loan.setPropertyAddress(propertyAddress);

        return loan;
    }
}
