package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.Usuario;
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
	@ApiOperation(value = "Busca um usuário", response = Usuario.class, notes = "Essa operacao busca um usuário pelo nome informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um usuário com uma mensagem de sucesso", response = Usuario.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado nenhum usuário sera lancado um exception com o erro") })
	/*
	 * @param String name o metodo retorna o usuário que foi buscado pelo email
	 * 
	 * @return o Usuário Buscado
	 */
	@GetMapping(path = "/{email}")
	public ResponseEntity<Usuario> findUserByEmail(@Validated @PathVariable String email) {
		Usuario users = null;
		users = this.service.findUserByEmail(email);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Busca todos os usuários", response = Usuario.class, notes = "Essa operacao retorna todos os usuários")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna uma lista dos usuários com uma mensagem de sucesso", response = Usuario.class),
			@ApiResponse(code = 400, message = "Caso não for encontrado nenhum usuário sera lançado uma exception com o erro") })
	/*
	 * @param Int page Int size esse metodo retorna todos os usuários atraves de
	 * uma Pageable
	 * 
	 * @return retorna uma page dos usuários
	 */
	@GetMapping
	public ResponseEntity<Page<Usuario>> findAll(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) {
		Page<Usuario> resultPage = null;
		resultPage = this.service.findAll(page, size);	
		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}
	
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Ativa e Desativa um Usuário", notes = "Essa operacao retorna uma mensagem de sucesso")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Retorna uma mensagem de sucesso"),
			@ApiResponse(code = 500, message = "Caso nao for encontrado o usuário sera lancado um exception	") })
	/*
	 * @param String email esse metodo faz um update na classe email por
	 * completo
	 * 
	 * @return httpStatus 204
	 */
	@PatchMapping(path = "/{email}")
	public ResponseEntity<HttpStatus> ativarUsuarios(@Validated @PathVariable String email) {
		this.service.desativarOrAtivarUsuario(email);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Salva um usuário", response = Usuario.class, notes = "Essa operacao retorna o usuário salvo")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o usuário salvo com uma mensagem de sucesso", response = Usuario.class),
			@ApiResponse(code = 400, message = "Caso for encotrado algum erro no resquest sera lancado uma exception") })
	/*
	 * @param User esse metodo salva o usuário e o retorna
	 * 
	 * @return Usuario
	 */
	@PostMapping()
	public ResponseEntity<Usuario> saveUsers(@Validated @RequestBody Usuario users) {
		Usuario returnUsers = null;
		returnUsers = this.service.insertUser(users);
		return new ResponseEntity<>(returnUsers, HttpStatus.CREATED);
	}
	
	/*
	 * @param Usuario esse metodo faz um update na classe User por completo
	 * 
	 * @return User
	 */
	@PutMapping()
	public ResponseEntity<Usuario> updateUsers(@Validated @RequestBody Usuario users) {
		Usuario returnUsers = null;
		returnUsers = this.service.insertUser(users);
		return new ResponseEntity<>(returnUsers, HttpStatus.NO_CONTENT);
	}
	
}
