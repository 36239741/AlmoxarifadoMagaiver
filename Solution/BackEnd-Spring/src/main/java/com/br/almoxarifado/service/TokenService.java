package com.br.almoxarifado.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.almoxarifado.entity.TokenGenerate;
import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.repository.TokenRepository;

@Service
@Transactional
public class TokenService {
	@Autowired
	private TokenRepository repository;

	public UUID tokenGenerate(User user) {
		TokenGenerate token = new TokenGenerate();
		TokenGenerate returnToken = null;
		token.setUserId(user);
		token.setTempoExpiracao(this.tokenDataExpiracao());
		returnToken = this.repository.save(token);
		return returnToken.getToken();

	}

	public TokenGenerate findToken(UUID token) {
		return this.repository.findToken(token);

	}
	
	public void tokenUse(UUID token) {
		this.repository.desativeToken(token);
	}

	public Boolean tokenValidate(TokenGenerate token) {
		TokenGenerate returnToken = null;
		Boolean tokenValidate = false;
		LocalDateTime dataAtual = null;
		Integer compareDate = 0;
		dataAtual = LocalDateTime.now();
		returnToken = this.repository.findToken(token.getToken());
		if (returnToken.getTokenStatus() == true) {
			compareDate = dataAtual.compareTo(returnToken.getTempoExpiracao());
			if(compareDate < 0) {
				tokenValidate = true;
			}else {
				this.tokenUse(token.getToken());
			}
		}
		return tokenValidate;
	}

	public LocalDateTime tokenDataExpiracao() {
		LocalDateTime dataExpiracao = null;
		LocalDateTime dataAtual = null;
		dataAtual = LocalDateTime.now();
		dataExpiracao = dataAtual.plusMinutes(5L);
		return dataExpiracao;

	}
}
