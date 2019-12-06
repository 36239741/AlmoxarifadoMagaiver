package com.br.almoxarifado.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.dto.DtoUser;
import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.enums.UserRolesEnums;
import com.br.almoxarifado.service.UserService;

public class TestUserService extends AbstractIntegrationTest{
	
	@Autowired
	private UserService userService;
	
	
										/* TESTE DE INSERIR USUÁRIO */
	@Sql(scripts = {
			"/dataset/truncateUser.sql"
	})
	@Test
	public void userServiceSaveMustPass() {
		
		User user = new User();
		DtoUser dtoUser = null;
		
		user.setNome("henrique");
		user.setEmail("henrique_nitatori@hotmail.com");
		user.setSenha("123213123123");
		user.setStatusConta(true);
		user.setUserRoles(UserRolesEnums.ADMIN);
		
		dtoUser = this.userService.insertUser(this.userService.convertUser(user));
				
		/*TESTE*/
		Assert.assertNotNull(user);
		Assert.assertEquals("henrique",user.getNome());
	}
	
							/* TESTE DE BUSCAR TODOS OS USUÁRIOS */
	@Test
	public void testUserFindAllMustPass() {
	
		Page<User> returnUsers = null;
		
		/*CONSTRUCAO*/
		returnUsers = this.userService.findAll(0, 1);						
						
		/*TESTE*/
		Assert.assertNotNull(userService);
	}
}
