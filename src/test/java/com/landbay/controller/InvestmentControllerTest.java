package com.landbay.controller;

import com.landbay.model.internal.InvestmentPeriod;
import com.landbay.model.internal.Loan;
import com.landbay.service.InvestmentService;
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

import static com.landbay.testdata.DateTestData.*;
import static com.landbay.testdata.InvestmentTestData.*;
import static com.landbay.testdata.LoanTestData.getTestLoan;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InvestmentController.class)
public class InvestmentControllerTest {

    private static final Loan TEST_LOAN = getTestLoan();
    private static final int LENDER_ID = 1;
    private static final int TOTAL_INTEREST_OWED = 20;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @MockBean
    private InvestmentService investmentService;

    @Before
    public void setup() {
        given(loanService.getLoan(1)).willReturn(TEST_LOAN);
        given(investmentService.createInvestment(testInvestmentCreateRequest(), TEST_LOAN)).willReturn(testInvestment());
        InvestmentPeriod investmentPeriod = new InvestmentPeriod(yesterday(), oneMonth());
        given(investmentService.calculateInterestOwed(LENDER_ID, investmentPeriod)).willReturn(interestOwedResult(TOTAL_INTEREST_OWED));
    }

    @Test
    public void createInvestmentReturns200() throws Exception {
        mockMvc.perform(post("/api/investment")
                .content(testInvestmentCreateJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void createInvestmentContentType() throws Exception {
        mockMvc.perform(post("/api/investment")
                .content(testInvestmentCreateJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void createInvestmentReturnsJson() throws Exception {
        mockMvc.perform(post("/api/investment")
                .content(testInvestmentCreateJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("amount", is(1000)))
                .andExpect(jsonPath("lenderId", is(1)))
                .andExpect(jsonPath("loan.id", is(1)))
                .andExpect(jsonPath("loan.amount", is(20000000)))
                .andExpect(jsonPath("loan.annualInterest", is(3.40)))
                .andExpect(jsonPath("loan.term", is(25)))
                .andExpect(jsonPath("loan.endDate", is("2043-10-04")))
                .andExpect(jsonPath("loan.propertyAddress.number", is(18)))
                .andExpect(jsonPath("loan.propertyAddress.street", is("London Road")))
                .andExpect(jsonPath("loan.propertyAddress.city", is("London")))
                .andExpect(jsonPath("loan.propertyAddress.postCode", is("CR7 7PB")));
    }

    @Test
    public void getMonthlyInterestReturns200() throws Exception {
        mockMvc.perform(get("/api/investment/interest/" + LENDER_ID)
                .param("start", YESTERDAY)
                .param("end", ONE_MONTH)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getMonthlyInterestReturnsContentType() throws Exception {
        mockMvc.perform(get("/api/investment/interest/" + LENDER_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("start", YESTERDAY)
                .param("end", ONE_MONTH))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void getMonthlyInterestReturnsJson() throws Exception {
        mockMvc.perform(get("/api/investment/interest/" + LENDER_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("start", YESTERDAY)
                .param("end", ONE_MONTH))
                .andExpect(jsonPath("total", is(TOTAL_INTEREST_OWED)));
    }

    private String testInvestmentCreateJson() {
        return "{\n" +
                "\t\"amount\": 1000,\n" +
                "\t\"loanId\": 1,\n" +
                "\t\"lenderId\": 1,\n" +
                "\t\"startDate\": \"" + YESTERDAY + "\",\n" +
                "\t\"endDate\": \"" + ONE_MONTH + "\"\n" +
                "}";
    }
}
