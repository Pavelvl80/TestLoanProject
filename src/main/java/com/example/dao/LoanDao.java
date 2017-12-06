package com.example.dao;

import com.example.model.Loan;
import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
public interface LoanDao extends CrudRepository<Loan, Long> {
    List<Loan> findByPerson(Person person);
}
