package com.tr.repository;


import com.tr.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface PersonRepository extends CrudRepository <Person, Long> {

    public Person findByFirstName(String firstName);
    public Person findByLastName(String lastName);
}
