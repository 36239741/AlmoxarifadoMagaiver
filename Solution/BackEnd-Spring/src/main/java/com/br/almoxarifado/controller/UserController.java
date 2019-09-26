package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService service ;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<?>  findAllUsers(){
		Page<User> user = null;
		user = this.service.findAll(1, 1);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}


}
