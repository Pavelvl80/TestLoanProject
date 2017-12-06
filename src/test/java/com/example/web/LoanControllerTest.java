package com.example.web;

import com.example.model.Loan;
import com.example.model.Person;
import com.example.service.BlackListService;
import com.example.service.FilterService;
import com.example.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlackListService blacks;

    @MockBean
    private LoanService loans;

    @MockBean
    private FilterService filter;

    public LoanControllerTest() {
    }

    @Test
    public void whenPersonNotInBlackListThenApplyLoan() throws Exception {
        List<Loan> list = Collections.singletonList(
                new Loan(1D, "asd", new Person("johny", "guitar")));
        given(loans.getAll()).willReturn(list);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void whenLoadThenApplyLoan() throws Exception {
        List<Loan> list = Collections.singletonList(
                new Loan(1D, "asd", new Person("johny", "guitar")));
        ObjectMapper mapper = new ObjectMapper();
        given(loans.getByPersonId(0)).willReturn(list);
        mvc.perform(get("/0")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void whenApplyThenSave() throws Exception {
        Loan loan = new Loan(1D, "asd", new Person("johny", "guitar"));
        ObjectMapper mapper = new ObjectMapper();
        given(blacks.isPersonBlackListed(0)).willReturn(false);
        given(loans.save(loan)).willReturn(loan);
        mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(loan)))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(loan)));
    }

    @Test
    public void whenInBlacklistThenError() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given(this.blacks.isPersonBlackListed(0)).willReturn(true);
        this.mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(new Loan(1D, "asd", new Person("johny", "guitar")))))
                .andExpect(status().isOk())
                .andExpect(content().string("User 0 in blacklist"));
    }
}
