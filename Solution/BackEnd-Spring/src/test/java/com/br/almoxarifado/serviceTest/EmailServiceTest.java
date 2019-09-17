package com.br.almoxarifado.serviceTest;

import java.util.UUID;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.service.EmailService;

public class EmailServiceTest extends AbstractIntegrationTest {
	@Autowired
	private EmailService service;
	@Test
	public void sendEmailMustPass() throws MessagingException {
		this.service.enviarEmail("henrique_nitatori@hotmail.com","active",this.service.messageStructure("henrique_nitatori@hotmail.com",UUID.randomUUID()));
	}
}
