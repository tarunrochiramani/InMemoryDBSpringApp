package com.tr.repository;

import com.tr.spring.InMemoryDBAppConfiguration;
import com.tr.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = InMemoryDBAppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(value = "classpath:/test-application.properties")
public class PersonRepositoryTest {
    private final static String FIRST_NAME = "abcd";
    private final static String LAST_NAME = "def";

    private Person person;
    @Autowired private PersonRepository personRepository;

    @Before
    public void setUp() {
        person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);

        person = personRepository.save(person);
    }

    @After
    public void cleanUp() {
        personRepository.delete(person);
    }

    @Test
    public void canFindByFirstName() {
        Person personToVerify = personRepository.findByFirstName(FIRST_NAME);

        assertNotNull(personToVerify);
        assertEquals(person.getFirstName(), personToVerify.getFirstName());
        assertEquals(person.getLastName(), personToVerify.getLastName());

    }

    @Test
    public void canFindByLastName() {
        Person personToVerify = personRepository.findByLastName(LAST_NAME);

        assertNotNull(personToVerify);
        assertEquals(person.getFirstName(), personToVerify.getFirstName());
        assertEquals(person.getLastName(), personToVerify.getLastName());

    }
}
