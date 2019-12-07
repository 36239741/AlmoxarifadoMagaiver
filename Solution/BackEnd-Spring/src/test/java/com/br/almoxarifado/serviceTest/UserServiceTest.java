package com.br.almoxarifado.serviceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.entity.Usuario;
import com.br.almoxarifado.service.UserService;

public class UserServiceTest extends AbstractIntegrationTest{
	
	@Autowired
	private UserService userService;
	
	@Test()
	public void userServiceSaveMustPass() {
		Usuario user = new Usuario();
		user.setNome("henrique");
		user.setEmail("henrique_nitatori@hotmail.com");
		user.setSenha("123213123123");
		this.userService.saveUser(user);
	}

}
