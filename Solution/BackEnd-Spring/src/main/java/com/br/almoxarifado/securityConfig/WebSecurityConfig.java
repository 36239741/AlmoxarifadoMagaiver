package com.br.almoxarifado.securityConfig;

import java.time.Duration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and()
		.csrf().disable();
		
		
	}
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
			http
	        .headers()
	            .hsts()
	                .includeSubdomains(true)
	                .maxAge(Duration.ofDays(360L));
	    return http.build();
	    
	}
		
	
}
