package com.ouhamza.springSec.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
// spring mvc activation et component scan et view resolver

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.ouhamza.springSec.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

	// set up a variable to hold the properties
	@Autowired
	private Environment env;
	
	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	
    // define a bean for viewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	
	// define a bean for a datasource
	
	@Bean
	public DataSource getDatasource() {
		// create the connection pool
		
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		
		// set the jdbc dirver
		
		try {
			comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		// log the connection propers
		
		logger.info("Jdbc Driver ===>"+ env.getProperty("jdbc.driver"));
		
		//set databases connection properties
		comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
		comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool
		comboPooledDataSource.setInitialPoolSize(convertProperty("connection.pool.initialPoolSize"));
		comboPooledDataSource.setMinPoolSize(convertProperty("connection.pool.minPoolSize"));
		comboPooledDataSource.setMaxPoolSize(convertProperty("connection.pool.maxPoolSize"));
		comboPooledDataSource.setMaxIdleTime(convertProperty("connection.pool.maxIdleTime"));
		
		return comboPooledDataSource;
	}
	
	private int convertProperty(String propetyName) {
		return Integer.parseInt(env.getProperty(propetyName));
	}
}
