package com.br.almoxarifado.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.repository.UserRepository;
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository repository;

	
	public User createOrUpdate(User user) {
		User findByEmail = null;
		findByEmail = this.findByemail(user.getEmail());
			if(findByEmail == null) {
				this.repository.save(user);
			}
	return user;
		
	}

	
	public Optional<User> findById(Long id) {
		Optional<User> findById =null;
		try {
			findById = this.repository.findById(id);
		} catch (Exception e) {
			e.getMessage();
		}
		return findById;
	}

	
	public void delete(Long id) {
		Optional<User> findById = null;
		findById = findById(id);
		if(findById != null) {
			this.repository.deleteById(id);
		}
		
	}

	
	public Page<User> findAll(int page, int count) {
		PageRequest pages = PageRequest.of(page, count);
		return this.repository.findAll(pages);
	}

	
	public User findByemail(String email) {
		User findByEmail = null;
		try {
			findByEmail = this.repository.findByEmailContaining(email);
		}catch(Exception e) {
			e.getMessage();
		}
	return findByEmail;
	}

}
