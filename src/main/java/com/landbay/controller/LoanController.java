package com.landbay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/loan")
public class LoanController {

    @GetMapping(value = "/{loanId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getLoan(@PathVariable(value = "loanId") String loanId) {
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
