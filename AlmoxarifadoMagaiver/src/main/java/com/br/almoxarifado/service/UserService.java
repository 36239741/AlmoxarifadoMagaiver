package com.br.almoxarifado.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;
	
	//Insere um user verificando se ja tem um email cadastrado
	public User insertUser(User user) {
		String filterEmail = null;
		User findByEmail = null;
		User userPersist = null;
		filterEmail = user.getEmail();
		findByEmail = this.findByemail(filterEmail);
		if (findByEmail == null) {
			user.setEmail(filterEmail);
			userPersist =  this.repository.save(user);
		}
	return userPersist;
	}
	//Edita um item somente se encontra o usuario por email no banco
	public User updateUser(User user) {
		User findByEmail = null;
		User userPersist = null;
		findByEmail = this.findByemail(user.getEmail());
		if (findByEmail != null) {
			userPersist=this.repository.save(user);
		}
		return userPersist;
	}
	//busca um usuario pelo id
	public Optional<User> findById(Long id) {
		Optional<User> findById = null;
		try {
			findById = this.repository.findById(id);
		} catch (Exception e) {
			e.getMessage();
		}
		return findById;
	}


	//Retorna todos usuarios
	public Page<User> findAll(int page, int count) {
		PageRequest pages = PageRequest.of(page, count);
		return this.repository.findAll(pages);
	}
	//Busca pelo email do usuario
	public User findByemail(String email) {
		User findByEmail = null;
		try {
			findByEmail = this.repository.findByEmail(email);
		} catch (Exception e) {
			e.getMessage();
		}
		return findByEmail;
	}
	//Ativa o usuario se tiver desativado e vice e versa
	public void activeOrDesative(String email) {
		User userFindByEmail = null;
		userFindByEmail = this.repository.findByEmail(email);
		if(userFindByEmail != null) {
			if(userFindByEmail.isStatusUser() == true) {
				this.repository.userDesative(userFindByEmail.getId());
			}
			else {
				this.repository.userDesative(userFindByEmail.getId());
			}
		}
	}

}
