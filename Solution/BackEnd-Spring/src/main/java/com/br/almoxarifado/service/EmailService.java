package com.br.almoxarifado.service;

import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	public void enviarEmail(String email, String assunto, String mensagemHtml) throws MessagingException {

		MimeMessage message = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(email);
		helper.setSentDate(new Date());
		helper.setSubject(assunto);
		helper.setText(mensagemHtml, true);
		this.mailSender.send(message);

	}

	public String messageStructure(String email, UUID uuid) {
		String url = "http://localhost:8080/active/" + uuid;
		return "<html><body><h1>Ativacao da Conta</h1>" + "<br/>" + "<b><p>Usuario: </p></b> " + email
				+ "<p>Para ativar <a href='" + url + "'>Clique Aqui</a></body></html>";

		
	}
	public String messageStructureChangePassword(String email, String senha) {
		return  "<html><body><h1>Troca de Senha</h1>" + "<br/>" + "<b><p>Usuario: </p></b> " + email
				+ "Nova senha<"+senha+"/body></html>";

		
	}
}
