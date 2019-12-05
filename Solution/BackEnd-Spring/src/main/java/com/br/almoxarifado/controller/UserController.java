package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.br.almoxarifado.dto.DtoUser;
import com.br.almoxarifado.entity.User;
import com.br.almoxarifado.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping(path = "v1/usuarios")
public class UserController {
	@Autowired
	public UserService service;
	
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um usuário", response = DtoUser.class, notes = "Essa operacao busca um usuário pelo nome informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna um usuário com uma mensagem de sucesso", response = DtoUser.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado nenhum usuário sera lancado um exception com o erro") })
	/*
	 * @param String name o metodo retorna o usuário que foi buscado pelo nome
	 * 
	 * @return o Usuário Buscado
	 */
	@GetMapping(path = "/{email}")
	public ResponseEntity<DtoUser> findUserByEmail(@Validated @PathVariable String email) {
		User users = null;
		users = this.service.findUserByEmail(email);
		DtoUser dto = this.service.convertUser(users);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
