package com.tr;

import javax.sql.DataSource;

import com.tr.spring.InMemoryDBAppConfiguration;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = InMemoryDBAppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(value = "classpath:/test-application.properties")
public class InMemoryDBAppConfigurationTest {

    @Autowired private DataSource dataSource;

    @Test
    public void dataSourceExists() {
        assertNotNull(dataSource);
    }

}
