package com.bayram.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.bayram")
@EnableTransactionManagement
public class AppConfig {
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setPassword("1234");
		dataSource.setUsername("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/okul?serverTimezone=Turkey");
		return dataSource;
	}
	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabase(Database.MYSQL);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.bayram.entities");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		factory.setJpaProperties(getProperties());
		return factory.getObject();
	}
	
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	@Bean
	@Autowired
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
		
		JpaTransactionManager jpaTransactionManager=new JpaTransactionManager(emf);
		
		return jpaTransactionManager;
		
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new  PersistenceExceptionTranslationPostProcessor();

	}


	
	

	
}