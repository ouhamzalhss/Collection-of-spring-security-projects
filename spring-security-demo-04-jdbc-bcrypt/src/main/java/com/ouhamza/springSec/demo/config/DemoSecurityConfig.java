package com.ouhamza.springSec.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
// spring security config

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// use jdbc authentication
		auth.jdbcAuthentication().dataSource(datasource);
	
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.csrf().disable()
		.authorizeRequests()
	
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/systems/**").hasRole("ADMIN")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.and()
		.formLogin()
			.loginPage("/myLogin")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout()
			.permitAll()
		.and()
		.exceptionHandling()
			.accessDeniedPage("/access-denied");
	}
}
