package com.br.almoxarifado.service;

import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.TokenGenerate;
import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private TokenService tokenService;

	private GeradorSenhaService geradorSenha;

	// Insere um user verificando se ja tem um email cadastrado
	public User insertUser(User user) throws MessagingException {
		String filterEmail = null;
		String codedPassword;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User findByEmail = null;
		User userPersist = null;
		UUID token = null;
		filterEmail = user.getEmail();
		findByEmail = this.findByemail(filterEmail);
		if (findByEmail == null) {
			codedPassword = passwordEncoder.encode(user.getSenha());
			user.setSenha(codedPassword);
			user.setEmail(filterEmail);
			userPersist = this.repository.save(user);
			if (userPersist != null) {
				token = this.tokenService.tokenGenerate(userPersist);
				this.emailService.enviarEmail(userPersist.getEmail(), "Active Account",
						this.emailService.messageStructure(userPersist.getEmail(), token));
			}
		}
		return userPersist;
	}

	// Edita um item somente se encontra o usuario por email no banco
	public User updateUser(User user) throws MessagingException {
		User findByEmail = null;
		User userPersist = null;
		findByEmail = this.findByemail(user.getEmail());
		if (findByEmail != null) {
			userPersist = this.repository.save(user);
		}
		return userPersist;
	}

	// busca um usuario pelo id
	public Optional<User> findById(Long id) {
		Optional<User> findById = null;
		try {
			findById = this.repository.findById(id);
		} catch (Exception e) {
			e.getMessage();
		}
		return findById;
	}

	// Retorna todos usuarios
	public Page<User> findAll(int page, int count) {
		PageRequest pages = PageRequest.of(page, count);
		return this.repository.findAll(pages);
	}

	// Busca pelo email do usuario
	public User findByemail(String email) {
		User findByEmail = null;
		try {
			findByEmail = this.repository.findByEmail(email);
		} catch (Exception e) {
			e.getMessage();
		}
		return findByEmail;
	}

	// Ativa o usuario se tiver desativado e vice e versa
	public void activeOrDesative(String email) {
		User userFindByEmail = null;
		userFindByEmail = this.repository.findByEmail(email);
		if (userFindByEmail != null) {
			if (userFindByEmail.isStatusUser() == true) {
				this.repository.userDesative(userFindByEmail.getId());
			} else {
				this.repository.userDesative(userFindByEmail.getId());
			}
		}
	}

	public TokenGenerate activeOrAccountLost(UUID token) {
		TokenGenerate findToken = null;
		findToken = this.tokenService.findToken(token);
		if (findToken.getTokenStatus() == true) {
			this.repository.userActiveAccount(findToken.getUserId().getId());
		} else {
			findToken = null;
		}
		return findToken;
	}
	public Integer changePassword(String email) throws MessagingException {
		String passwordGeneretad = null;
		Integer returnChangePassword = 0;
		User returnUser = null;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		returnUser = this.findByemail(email);
		if(returnUser != null) {
			passwordGeneretad = this.geradorSenha.gerarSenhaAleatoria();
			returnChangePassword = this.repository.userChangePassword(passwordEncoder.encode(passwordGeneretad), email);
			this.emailService.enviarEmail(email,"Troca de Senha",this.emailService.messageStructureChangePassword(email, passwordGeneretad));
		}
		return returnChangePassword;
	}

}
