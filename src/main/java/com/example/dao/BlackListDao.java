package com.example.dao;

import com.example.model.BlackList;
import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
public interface BlackListDao extends CrudRepository<BlackList, Long> {
   BlackList findByPerson(Person person);
}
