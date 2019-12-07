package com.br.almoxarifado.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.TokenGenerate;
import com.br.almoxarifado.entity.Usuario;
import com.br.almoxarifado.repository.UserRepository;

@Service
@Transient
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(Usuario user) {
		this.userRepository.save(user);
	}
	
	public UUID tokenGenerato() {
		TokenGenerate token = new TokenGenerate();
		TokenGenerate returnToken = null;
		token.setTempoExpiracao(this.tokenDataExpiracao());
		return token;
	}
	public LocalDateTime tokenDataExpiracao() {
		LocalDateTime dataExpiracao = null;
		LocalDateTime dataAtual = null;
		dataAtual = LocalDateTime.now();
		dataExpiracao = dataAtual.plusMinutes(5L);
		return dataExpiracao;

	}
}
