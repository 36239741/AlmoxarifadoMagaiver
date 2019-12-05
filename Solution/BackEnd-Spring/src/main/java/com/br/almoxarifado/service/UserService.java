package com.br.almoxarifado.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.br.almoxarifado.dto.DtoUser;
import com.br.almoxarifado.entity.TokenGenerate;
import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.repository.UserRepository;

import antlr.collections.List;

@Service
@Transient
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user) {
		this.userRepository.save(user);
	}
	
	public TokenGenerate tokenGenerato() {
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
	public User findUserByEmail(String email){
		User user = this.userRepository.findByEmail(email);
		Assert.notNull(user, "Usuário " + email + " nao encontrado");
		return user;
	}
	
	/* METODOS DE CONVERSAO DTO */
	
	public User convertDtoUser(DtoUser dtoUser) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dtoUser, User.class);

	}
	public DtoUser convertUser(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, DtoUser.class);
		
	}
}
