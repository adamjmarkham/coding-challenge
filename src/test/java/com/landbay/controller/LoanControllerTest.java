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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    private static final Loan TEST_LOAN = getTestLoan();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Before
    public void setup() {
        given(loanService.getLoan(1)).willReturn(TEST_LOAN);
    }

    @Test
    public void getALoanReturns200() throws Exception {
        mockMvc.perform(get("/api/loan/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createALoanReturns200() throws Exception {
        String loanBody = testLoanJson();

        mockMvc.perform(post("/api/loan")
                .content(loanBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getALoanReturnsContentType() throws Exception {
        mockMvc.perform(get("/api/loan/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void createALoanReturnsContentType() throws Exception {
        String loanBody = testLoanJson();

        mockMvc.perform(post("/api/loan")
                .content(loanBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
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

    @Test
    public void creatingALoanReturnsLoanId() throws Exception {
        String loanBody = testLoanJson();

        Loan testLoan = testLoanWithoutId();

        given(loanService.createLoan(testLoan)).willReturn(1);

        mockMvc.perform(post("/api/loan")
                .content(loanBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("id", is(1)));
    }

    private Loan testLoanWithoutId() {
        Loan testLoan = getTestLoan();
        testLoan.setId(null);
        return testLoan;
    }

    private String testLoanJson() {
        return "{\n" +
                "\t\"amount\": 20000000,\n" +
                "\t\"annualInterest\": 3.4,\n" +
                "\t\"propertyAddress\": {\n" +
                "\t\t\"street\": \"London Road\",\n" +
                "\t\t\"number\": 18,\n" +
                "\t\t\"city\": \"London\",\n" +
                "\t\t\"postCode\": \"CR7 7PB\"\n" +
                "\t},\n" +
                "\t\"term\": 25,\n" +
                "\t\"investments\": null,\n" +
                "\t\"endDate\": \"2043-10-04\"\n" +
                "}";
    }

    private static Loan getTestLoan() {
        Loan loan = new Loan();
        loan.setId(1);
        loan.setAnnualInterest(new BigDecimal("3.4"));
        loan.setTerm(25);
        loan.setAmount(20000000);
        loan.setEndDate("2043-10-04");

        PropertyAddress propertyAddress = new PropertyAddress();
        propertyAddress.setNumber(18);
        propertyAddress.setStreet("London Road");
        propertyAddress.setCity("London");
        propertyAddress.setPostCode("CR7 7PB");
        loan.setPropertyAddress(propertyAddress);

        return loan;
    }
}
