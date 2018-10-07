package com.landbay.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}
