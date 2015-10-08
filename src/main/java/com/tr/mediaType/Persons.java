package com.tr.mediaType;

import java.io.Serializable;
import java.util.List;

import com.tr.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class Persons implements Serializable {
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
