package com.tr.rest;

import com.tr.mediaType.Persons;
import com.tr.spring.RestTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestTestConfig.class)
@WebIntegrationTest ("server.port:9000")
@TestPropertySource(value = "classpath:/test-application.properties")
public class PersonControllerTest {

    private RestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void canGetPersons() {
        ResponseEntity<Persons> persons = restTemplate.getForEntity("http://localhost:9000/rest/persons", Persons.class);

        Assert.assertNotNull(persons);
    }
}
