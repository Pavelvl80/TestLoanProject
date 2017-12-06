package com.example.service;

import com.example.dao.BlackListDao;
import com.example.dao.PersonDao;
import com.example.model.BlackList;
import com.example.model.Person;
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
public class BlackListServiceTest {


    @Autowired
    private PersonDao personsDao;

    @Autowired
    private BlackListDao blackListDao;

    @Autowired
    private BlackListService blackListService;

    @Test
    public void whenPersonInBlackListThenReturnTrue() {
        Person person = this.personsDao.save(new Person("johny", "guitar"));
        this.blackListDao.save(new BlackList(person));
        boolean result = this.blackListService.isPersonBlackListed(person.getId());
        assertTrue(result);
    }

    @Test
    public void whenBlackListEmptyThenAnyPersonNotIn() {
        Person person = this.personsDao.save(new Person("johny", "guitar"));
        boolean result = this.blackListService.isPersonBlackListed(person.getId());
        assertFalse(result);
    }
}
