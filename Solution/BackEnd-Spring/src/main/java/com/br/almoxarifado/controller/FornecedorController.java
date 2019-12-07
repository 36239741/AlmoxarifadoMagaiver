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

import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.service.FornecedorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping(path = "v1/fornecedores")
public class FornecedorController {
	@Autowired
	private FornecedorService service;

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um fornecedor", response = Fornecedor.class, notes = "Essa operacao busca um fornecedor pelo nome informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna um Fornecedor com uma mensagem de sucesso", response = Fornecedor.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado nenhum fornecedor sera lancado um exception com o erro") })
	/*
	 * @param String name o metodo retorna o fornecedor que foi byscado pelo nome
	 * 
	 * @return o Fornecedor Buscado
	 */
	@GetMapping(path = "/{name}")
	public ResponseEntity<Fornecedor> findFonecedoresByName(@Validated @PathVariable String name) {
		Fornecedor fornecedor = this.service.findByNomeFornecedor(name);
		return ResponseEntity.ok(fornecedor);
	}

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Busca todos fornecedores", response = Fornecedor.class, notes = "Essa operacao retorna uma Page de  todos os fornecedores")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna uma lista de Fornecedor com uma mensagem de sucesso", response = Fornecedor.class),
			@ApiResponse(code = 400, message = "Caso nao for encontrado nenhum fornecedor sera lancado um exception com o erro") })
	/*
	 * @param Int page Int size esse metodo retorna todos os fornecedores atraves de
	 * uma Pageable
	 * 
	 * @return retorna uma page de fornecedores
	 */
	@GetMapping
	public ResponseEntity<Page<Fornecedor>> findAll(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) {
		Page<Fornecedor> pageFornecedor = null;
		pageFornecedor = this.service.findAll(page, size);
		return new ResponseEntity<>(pageFornecedor, HttpStatus.OK);
	}
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Salva um fornecedor", response = Fornecedor.class, notes = "Essa operacao retorna o fornecedor salvo")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o fornecedor salvo com uma mensagem de sucesso", response = Fornecedor.class),
			@ApiResponse(code = 400, message = "Caso for encotrado algum erro no resquest sera lancado um exception") })
	/*
	 * @param Fornecedor esse metodo salva um fornecedor e retorna ele
	 * 
	 * @return Fornecedor
	 */
	@PostMapping()
	public ResponseEntity<Fornecedor> saveFornecedores(@Validated @RequestBody Fornecedor fornecedor) {
		Fornecedor returnFornecedores = this.service.saveFornecedor(fornecedor);
		return ResponseEntity.ok(returnFornecedores);
	}
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Faz o update na classe por completo", response = Fornecedor.class, notes = "Essa operacao retorna o fornecedor salvo")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Retorna o fornecedor que foi feito o update", response = Fornecedor.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado o fornecedor que deseja atualizar sera lancado um exception") })
	/*
	 * @param Fornecedor esse metodo faz um update na classe fornecedor por completo
	 * 
	 * @return Fornecedor
	 */
	@PutMapping()
	public ResponseEntity<Fornecedor> updateFornecedores(@Validated @RequestBody Fornecedor fornecedores) {
		Fornecedor returnFornecedores = null;
		returnFornecedores = this.service.saveFornecedor(fornecedores);
		return new ResponseEntity<>(returnFornecedores, HttpStatus.NO_CONTENT);
	}
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Ativa e Desativa um Fornecedor", notes = "Essa operacao retorna uma mensagem de sucesso")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Retorna uma mensagem de sucesso"),
			@ApiResponse(code = 500, message = "Caso nao for encontrado o fornecedor sera lancado um exception	") })
	/*
	 * @param String name esse metodo faz um update na classe fornecedor por
	 * completo
	 * 
	 * @return httpStatus 204
	 */
	@PatchMapping(path = "/{name}")
	public ResponseEntity<HttpStatus> ativarFornecedores(@Validated @PathVariable String name) {
		this.service.desativarOrAtivarFornecedor(name);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
