package com.tr;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.tr.*")
@PropertySource("classpath:/application.properties")
@EnableJpaRepositories(basePackages = "com.tr.repository")
@EnableTransactionManagement
public class InMemoryDBAppConfiguration {

    private static Logger logger = Logger.getLogger(InMemoryDBAppConfiguration.class);

    @Autowired private Environment environment;

    @Bean (name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));


        return dataSource;
    }

    @Bean (name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.tr.*" });

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.datasource.dialect"));
        sessionFactory.setHibernateProperties(properties);

        return sessionFactory;
    }

    @Bean (destroyMethod = "stop")
    public Server createLocalTCPServer() throws SQLException {
        Server server = Server.createTcpServer("-tcp","-tcpAllowOthers","-tcpPort","8043");

        server.start();
        logger.info(server.getURL());
        return server;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(getDataSource());
        factory.setPackagesToScan("com.tr.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        factory.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", environment.getProperty("spring.datasource.dialect"));
        factory.setJpaProperties(jpaProperties);

        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public SpringLiquibase instantiateLiquibaseBean() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(getDataSource());
        springLiquibase.setChangeLog("classpath:/" + environment.getProperty("changelog.file"));

        return springLiquibase;
    }


}
