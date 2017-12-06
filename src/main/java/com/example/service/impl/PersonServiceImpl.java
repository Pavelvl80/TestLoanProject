package com.example.service.impl;

import com.example.dao.PersonDao;
import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDao personalDao;

    @Autowired
    public PersonServiceImpl(PersonDao personalDao) {
        this.personalDao = personalDao;
    }

    @Override
    public Person save(Person person) {
        return personalDao.save(person);
    }

    @Override
    public Person remove(Person person) {
        personalDao.delete(person);
        return person;

    }

    @Override
    public Person getById(long id) {
        return personalDao.findById(id);
    }
}
