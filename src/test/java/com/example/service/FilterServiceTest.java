package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Edvard Piri on 06.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterServiceTest {

    @Autowired
    private FilterService filterService;

    @Test
    public void whenLimitNotExceedThenFalse() {
        boolean result = filterService.filer("Ru");
        assertFalse(result);
    }

    @Test
    public void whenLimitNotExceedThanTrue() {
        filterService.filer("Ru");
        boolean result = filterService.filer("Ru");
        assertTrue(result);
    }
}
