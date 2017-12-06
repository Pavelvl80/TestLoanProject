package com.example.service;

import com.example.model.Loan;

import java.util.List;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
public interface LoanService {

    Loan save(Loan loan);

    List<Loan> getAll();

    List<Loan> getByPersonId(long id);
}
