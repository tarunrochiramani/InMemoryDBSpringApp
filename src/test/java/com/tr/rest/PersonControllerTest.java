package com.tr.rest;

import java.util.List;

import com.tr.InMemoryDBAppConfiguration;
import com.tr.mediaType.Persons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringApplicationConfiguration(classes = InMemoryDBAppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(value = "classpath:/test-application.properties")
@WebIntegrationTest
public class PersonControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void foo() {
        restTemplate.getForEntity("", Persons.class);
    }

}
