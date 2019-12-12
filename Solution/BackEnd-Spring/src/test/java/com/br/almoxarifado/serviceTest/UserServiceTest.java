package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;
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
		
		reuturnUser = this.userService.insertUser(user);
				
		/*TESTE*/
		Assert.assertNotNull(user);
		Assert.assertEquals("henrique",reuturnUser.getNome());
	}
							/* Alterar Usuario */
	@Test
	public void userServiceSaveMustPassAlterandoUmUsuario() {
		Usuario user = new Usuario();	
		Usuario reuturnUser = null;	
		
		user = this.userService.findUserByEmail("josiasmegabit@hotmail.com");
		System.out.println(user);
				
	
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
							/* TESTE DE BUSCAR UM USUÁRIO PELO EMAIL */
	@Sql(scripts = {
			"/dataset/truncateUser.sql",
			"/dataset/user.sql"
	})
	@Test
	public void testeUserFindByEmailMustPass() {
		/*ATRIBUTOS	*/
		Usuario usuario = null;
		
		/*CONSTRUCAO*/
		usuario = this.userService.findUserByEmail("josiasmegabit@hotmail.com");
									
		/*TESTE*/
		Assert.assertNotNull(usuario);
		Assert.assertEquals("josiasmegabit@hotmail.com", usuario.getEmail());
	}
	
						/* TESTE DE DESATIVAR USUARIO PELO EMAIL */
	@Sql(scripts = {
			"/dataset/truncateUser.sql",
			"/dataset/user.sql"
	})
	@Test
	public void testeUserAtivaDesativaMustPass() {
		/*ATRIBUTOS	*/
		Usuario usuario = null;
		
		/*CONSTRUCAO*/
		this.userService.desativarOrAtivarUsuario("josiasmegabit@hotmail.com");
		usuario = this.userService.findUserByEmail("josiasmegabit@hotmail.com");
									
		/*TESTE*/
		Assert.assertEquals(true, usuario.isUserStatus());
		
	}
	
	
											/* MUSTFAIL */
		
							/* CADASTRANDO USUARIO COM CAMPO NOME VAZIO */
	@Sql(scripts = {
	"/dataset/truncateUser.sql"
	})
	@Test(expected = TransactionSystemException.class)
	public void userSaveMustFailCadastrarUsuarioNomeEmBranco() {
		/*ATRIBUTOS	*/
		Usuario usuario = new Usuario();
		Usuario returnUser= null;
	
		/*CONSTRUCAO*/
		usuario.setNome("");
		usuario.setSenha("0113165656521");
		usuario.setEmail("josiasmegabit@hotmail.com");
		usuario.setUserStatus(true);
		usuario.setUserRoles(UserRolesEnums.ADMIN);
		usuario.setStatusConta(true);
		
		returnUser = this.userService.insertUser(usuario);
		
		/*TESTE*/
		Assert.assertNotNull(returnUser);
		Assert.assertEquals("Josias",returnUser.getNome());
		Assert.assertEquals("josiasmegabit@hotmail.com",returnUser.getEmail());
	

	}
	
								/* CADASTRANDO USUARIO COM EMAIL DUPLICADO */
	@Sql(scripts = {
			"/dataset/truncateUser.sql",
			"/dataset/user.sql"
	})
	@Test(expected = DataIntegrityViolationException.class)
	public void usuarioSaveMustFaillCadastrarComEmailDuplicado() {
		/*ATRIBUTOS	*/
		Usuario usuario = new Usuario();
		Usuario returnUsuario = null;
		
		/*CONSTRUCAO*/
		usuario.setNome("Josias Fonseca");
		usuario.setEmail("josiasmegabit@hotmail.com");
		usuario.setSenha("45454787854");
		usuario.setUserRoles(UserRolesEnums.USER);
		usuario.setUserStatus(true);
		usuario.setStatusConta(true);
		this.userService.insertUser(usuario);
		
		returnUsuario = this.userService.findUserByEmail(usuario.getEmail());
		
	
	}
	
	

									/* BUSCANDO USUARIO POR EMAIL INEXISTENTE */
	@Sql(scripts = {
		"/dataset/truncateUser.sql",
		"/dataset/user.sql"
	})
	@Test(expected = IllegalArgumentException.class)
	public void usuarioFindByEmailMustFailBuscaUsuarioInexistente() {
		/*ATRIBUTOS	*/
		
		Usuario usuario = null;
		
		/*CONSTRUCAO*/
		usuario = this.userService.findUserByEmail("teste@teste.com.br");
		Assert.assertNull(usuario);
	}
		
	/* BUSCANDO USUARIO POR EMAIL INEXISTENTE */
	@Sql(scripts = {
		"/dataset/truncateUser.sql",
		"/dataset/user.sql"
	})
	@Test(expected = IllegalArgumentException.class)
	public void usuarioMustFailExcluirUsuarioInexistente() {
		/*ATRIBUTOS	*/
		
		Usuario usuario = null;
		
		/*CONSTRUCAO*/
		Boolean excluir = this.userService.desativarOrAtivarUsuario("teste@teste.com.br");
		Assert.assertTrue(excluir);
}				
}
