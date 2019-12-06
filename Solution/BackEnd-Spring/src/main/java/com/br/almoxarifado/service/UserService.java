package com.br.almoxarifado.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	/*
	 * @param Users
	 * a funcao ira inserir um usuário
	 * @return return the save object
	 */	
	public DtoUser insertUser(DtoUser dtoUser) {
		User returnUsers = null;
		
		returnUsers = this.userRepository.save(this.convertDtoUser(dtoUser));
		
		return this.convertUser(returnUsers);
				
	}
	
	public TokenGenerate tokenGenerator() {
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
	
	/*
	 * @param integer page, integer pageSize
	 * ira fazer um find all e retornara em pageable
	 * @return page usuários
	 */		
	@Transactional(readOnly = true)
	public Page<User> findAll(Integer page, Integer pageSize) {
		Page<User> pageUsers;
		PageRequest pageable = PageRequest.of(page, pageSize);
		pageUsers = this.userRepository.findAll(pageable);
		return pageUsers;
	}
	
	/*
	 * @param String nome do fornecedor
	 * ira desativar e ativar o fornecedor
	 * @return void
	 */
	
	public Boolean desativarOrAtivarUsuario(String email) {
		User users = null;
		Boolean returnStatus = null;
		users = this.findUserByEmail(email);
		if (users != null) {

			if (users.isStatusConta()) {
				 this.userRepository.userDesative(users.getId());
				 returnStatus = false;
			} 
			else {
				 this.userRepository.userActive(users.getId());
				 returnStatus = true;
			}
			
		}
		return returnStatus;
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
