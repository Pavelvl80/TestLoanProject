package com.example.dao;

import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
public interface PersonDao extends CrudRepository<Person, Long>{
    Person findById(Long id);
}
