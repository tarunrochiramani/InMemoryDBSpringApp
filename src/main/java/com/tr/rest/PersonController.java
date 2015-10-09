package com.tr.rest;

import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tr.entity.Person;
import com.tr.mediaType.Persons;
import com.tr.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rest/persons")
public class PersonController {

    @Autowired private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Persons allPersons() {
        List<Person> persons = new ArrayList<Person>();
        Iterator<Person> iterable = personRepository.findAll().iterator();
        while (iterable.hasNext()) {
            persons.add(iterable.next());
        }

        Persons personList = new Persons();
        personList.setPersons(persons);

        return personList;
    }
}
