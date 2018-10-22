package com.landbay.repository;

import com.landbay.model.internal.Investment;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface InvestmentRepository extends CrudRepository<Investment, Integer> {

    Set<Investment> findByLenderId(int lenderId);
}
