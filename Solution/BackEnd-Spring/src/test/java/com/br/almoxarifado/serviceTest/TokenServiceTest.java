package com.br.almoxarifado.serviceTest;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.service.TokenService;
import com.br.almoxarifado.service.UserService;

public class TokenServiceTest extends AbstractIntegrationTest {
	@Autowired
	private TokenService service;
	@Autowired
	private UserService userService;
	
	@Test
	public void tokenGenerateMustPass() {
		this.service.tokenGenerate(this.userService.findByemail("henriquenitatori@hotmail.com"));
	}
	@Test
	public void tokenValidateMustPass() {
		Boolean validade = null;
		validade = this.service.tokenValidate(this.service.findToken(UUID.fromString("5a54781f-4fac-4863-9239-193a495ff668")));
		
	}
}
