package com.example.web;


/**
 * Created by Edvard Piri on 05.12.2017.
 */

import com.example.model.Loan;
import com.example.service.BlackListService;
import com.example.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanService loanService;
    private final BlackListService blackListService;

    @Autowired
    public LoanController(LoanService loanService, BlackListService blackListService) {
        this.loanService = loanService;
        this.blackListService = blackListService;
    }

    @PostMapping("/")
    public Object save(@RequestBody Loan loan) {
        if (!blackListService.isPersonBlackListed(loan.getPerson().getId()))
            return loan;
        return "User " + loan.getPerson().getId() + " in blacklist";
    }

    @GetMapping("/")
    public List<Loan> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/{personId}")
    public List<Loan> findByPersonId(@PathVariable long personId) {
        return loanService.getByPersonId(personId);
    }
}
