package com.tarefas.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value={"classpath:application.properties"})
public class PersistenceConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{"com.mvc.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", environment.getProperty("hibernate.temp.use_jdbc_metadata_defaults"));
		
		return properties;
	}

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		
		return dataSource;
	}
	
	
	@Autowired
	@Bean(name="transactionManager")
	public DataSourceTransactionManager getTransactionManager(DataSource dataSource){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		
		return transactionManager;
	}
	
}
