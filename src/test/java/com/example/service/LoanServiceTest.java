package com.example.service;

import com.example.model.Loan;
import com.example.model.Person;
import com.example.service.impl.LoanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {

    @Autowired
    LoanServiceImpl loanService;

    @Autowired
    PersonService personService;

    @Test
    public void serviceSaveTest() throws Exception {
        Person person = personService.save(new Person("johy", "guitar"));
        Loan loan = loanService.save(new Loan(1D, "test", person));
        List<Loan> result = loanService.getAll();
        assertTrue(result.contains(loan));
    }

    @Test
    public void serviceGetByIdTest() throws Exception {
        Person person = personService.save(new Person("test", "test"));
        Loan loan = loanService.save(new Loan(1D, "test", person));
        List<Loan> result = loanService.getByPersonId(person.getId());
        assertThat(result.iterator().next(), is(loan));
    }


}
