package com.example.service.impl;

import com.example.dao.LoanDao;
import com.example.model.Loan;
import com.example.model.Person;
import com.example.service.LoanService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Service
public class LoanServiceImpl implements LoanService{
    private LoanDao loanDao;

    @Autowired
    public LoanServiceImpl(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public Loan save(Loan loan) {
        return loanDao.save(loan);
    }

    @Override
    public List<Loan> getAll() {
        return Lists.newArrayList(loanDao.findAll());
    }

    @Override
    public List<Loan> getByPersonId(long id) {
        return loanDao.findByPerson(new Person(id));
    }
}
