package com.br.almoxarifado.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.TokenGenerate;
import com.br.almoxarifado.entity.Usuario;
import com.br.almoxarifado.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	
	/*
	 * @param Users
	 * a funcao ira inserir um usuário
	 * @return return the save object
	 */	
	public Usuario insertUser(Usuario usuario) {
		Usuario returnUsers = null;
		
		returnUsers = this.userRepository.save(usuario);
		
		return returnUsers;
				
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
	public Usuario findUserByEmail(String email){
		Usuario user = this.userRepository.findByEmail(email);
		Assert.notNull(user, "Usuário " + email + " nao encontrado");
		return user;
	}
	
	/*
	 * @param integer page, integer pageSize
	 * ira fazer um find all e retornara em pageable
	 * @return page usuários
	 */		
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Integer page, Integer pageSize) {
		Page<Usuario> pageUsers;
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
		Usuario users = null;
		Boolean returnStatus = null;
		users = this.findUserByEmail(email);
		if (users != null) {
			
			if (users.isUserStatus()) {
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

}
