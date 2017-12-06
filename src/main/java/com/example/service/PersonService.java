package com.example.service;

import com.example.model.Person;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
public interface PersonService {
    Person save(Person person);

    Person remove(Person person);

    Person getById(long id);
}
