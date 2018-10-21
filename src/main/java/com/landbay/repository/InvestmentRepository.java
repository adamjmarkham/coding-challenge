package com.landbay.repository;

import com.landbay.model.internal.Investment;
import org.springframework.data.repository.CrudRepository;

public interface InvestmentRepository extends CrudRepository<Investment, Integer> {

}
