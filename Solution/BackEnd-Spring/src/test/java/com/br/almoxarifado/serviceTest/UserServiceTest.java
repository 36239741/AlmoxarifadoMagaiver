package com.br.almoxarifado.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.entity.Usuario;
import com.br.almoxarifado.enums.UserRolesEnums;
import com.br.almoxarifado.service.UserService;

public class UserServiceTest extends AbstractIntegrationTest{
	
	@Autowired
	private UserService userService;
	
	
										/* TESTE DE INSERIR USUÁRIO */
	@Sql(scripts = {
			"/dataset/truncateUser.sql"
	})
	@Test
	public void userServiceSaveMustPassSalvandoUmUsuario() {
		Usuario user = new Usuario();	
		Usuario reuturnUser = null;	
		user.setNome("henrique");
		user.setEmail("henrique_nitatori@hotmail.com");
		user.setSenha("123213123123");
		user.setStatusConta(true);
		user.setUserRoles(UserRolesEnums.ADMIN);
		
		reuturnUser = this.userService.saveUser(user);
				
		/*TESTE*/
		Assert.assertNotNull(user);
		Assert.assertEquals("henrique",reuturnUser.getNome());
	}
	
							/* TESTE DE BUSCAR TODOS OS USUÁRIOS */
	@Test
	public void UserFindAllMustPassBuscandoTodosUsuarios() {
	
		Page<Usuario> returnUsers = null;
		
		/*CONSTRUCAO*/
		returnUsers = this.userService.findAll(0, 1);						
						
		/*TESTE*/
		Assert.assertNotNull(userService);
	}
}
