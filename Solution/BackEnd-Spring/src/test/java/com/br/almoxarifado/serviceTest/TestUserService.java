package com.br.almoxarifado.serviceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.service.UserService;

public class TestUserService extends AbstractIntegrationTest{
	
	@Autowired
	private UserService userService;
	
	@Test()
	public void userServiceSaveMustPass() {
		User user = new User();
		user.setNome("henrique");
		user.setEmail("henrique_nitatori@hotmail.com");
		user.setSenha("123213123123");
		this.userService.saveUser(user);
	}

}
