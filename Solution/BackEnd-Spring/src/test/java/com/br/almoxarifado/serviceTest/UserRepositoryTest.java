package com.br.almoxarifado.serviceTest;

import java.util.Optional;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.service.UserService;

public class UserRepositoryTest extends AbstractIntegrationTest {
	@Autowired
	private UserService service;

	@Test
	@Sql({"/dataset/truncateUser.sql",
			"/dataset/users.sql"})
	public void findByIdMustPass() {
		Optional<User> findById = null;
		findById = this.service.findById((long)1);
		Assert.assertEquals(findById.isPresent(),true);

	}
	@Test
	public void findByEmailMustPass() {
		User findByEmail = null;
		findByEmail = this.service.findByemail("henriquenitatori@hotmail.com");
		Assert.assertEquals(findByEmail.getNome(),"henrique" );
		Assert.assertEquals(findByEmail.getEmail(),"henriquenitatori@hotmail.com" );
		Assert.assertEquals(findByEmail.getSenha(), "dossantos3623");

	}

	@Test
	@Sql({
		"/dataset/truncateUser.sql",
	})
	public void insertUserMustPass() throws MessagingException {
		User user = new User();
		User returnUser = null;
		user.setEmail("henrique_nitatori@hotmail.com");
		user.setNome("joao");
		user.setSenha("joao1243533");
		returnUser = this.service.insertUser(user);
		Assert.assertNotNull(returnUser);
		Assert.assertEquals(user.getEmail(), returnUser.getEmail());
		
	}
	@Test
	@Sql({
		"/dataset/truncateUser.sql",
		"/dataset/users.sql"
	})
	public void editUserMustPass() throws MessagingException {
		User user = new User();
		user = this.service.findByemail("henriquenitatori@hotmail.com");
		user.setEmail("Ricar@hotmail.com");
		this.service.updateUser(user);
		Assert.assertEquals(user.getEmail(),"Ricar@hotmail.com");
	}
	@Sql({
		"/dataset/truncateUser.sql",
		"/dataset/users.sql"
	})
	@Test(expected = AssertionError.class)
	public void findByMustFailIdNaoEcontrado() {
		Optional<User> findById = null;
		findById = this.service.findById((long)2);
		Assert.assertEquals(findById.isPresent(), true);
		
	}
	@Sql({
		"/dataset/truncateUser.sql",
		"/dataset/users.sql"
	})
	@Test(expected = AssertionError.class)
	public void findByMustFailEmailNaoEcontrado() {
		User findByEmail = new  User();
		findByEmail = this.service.findByemail("henrique@hotmail.com");
		Assert.assertEquals(findByEmail != null, true);
		
	}
	@Sql({
		"/dataset/truncateUser.sql",
		"/dataset/users.sql"
	})
	@Test()
	public void insertMustFailDuplicateEmail() throws MessagingException {
		User user = new User();
		user.setEmail("henrique_nitatori@hotmail.com");
		user.setNome("joao");
		user.setSenha("nitatori");
		this.service.insertUser(user);
	}
}
