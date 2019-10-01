package com.br.almoxarifado.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.dto.DtoFornecedor;
import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.service.FornecedorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@CrossOrigin(origins = "https://localhost:8443")
@RequestMapping(path = "v1/fornecedores")
public class FornecedorController {
	@Autowired
	private FornecedorService service;

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um fornecedor", response = DtoFornecedor.class, notes = "Essa operacao busca um fornecedor pelo nome informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna um Fornecedor com uma mensagem de sucesso", response = DtoFornecedor.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado nenhum fornecedor sera lancado um exception com o erro") })
	/*
	 * @param String name o metodo retorna o fornecedor que foi byscado pelo nome
	 * 
	 * @return o Fornecedor Buscado
	 */
	@GetMapping(path = "/{name}")
	public ResponseEntity<DtoFornecedor> findFonecedoresByName(@Validated @PathVariable String name) {
		Fornecedor fornecedores = null;
		fornecedores = this.service.findByNomeFornecedor(name);
		DtoFornecedor dto = this.service.convertFornecedor(fornecedores);
		return new ResponseEntity<DtoFornecedor>(dto, HttpStatus.OK);
	}

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Busca todos fornecedores", response = DtoFornecedor.class, notes = "Essa operacao retorna uma Page de  todos os fornecedores")
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
	public ResponseEntity<List<DtoFornecedor>> findAll(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) {
		Page<Fornecedor> resultPage = null;
		List<DtoFornecedor> dto = null;
		resultPage = this.service.findAll(page, size);
		ModelMapper modelMapper = new ModelMapper();
		java.lang.reflect.Type targetListType = new TypeToken<List<DtoFornecedor>>() {}.getType();
		dto = modelMapper.map(resultPage.getContent(), targetListType);
		
		for (DtoFornecedor forn : dto) {
			String nome = forn.getNome();
			forn.add(linkTo(methodOn(FornecedorController.class).findFonecedoresByName(nome)).withSelfRel()
					.withRel("Find by name").withType("GET"));
			forn.add(linkTo(methodOn(FornecedorController.class).ativarFornecedores(nome)).withSelfRel()
					.withRel("Active or desative by name").withType("PATCH"));
		}
		
		return new ResponseEntity<List<DtoFornecedor>>(dto, HttpStatus.OK);
	}
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Salva um fornecedor", response = DtoFornecedor.class, notes = "Essa operacao retorna o fornecedor salvo")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o fornecedor salvo com uma mensagem de sucesso", response = Fornecedor.class),
			@ApiResponse(code = 400, message = "Caso for encotrado algum erro no resquest sera lancado um exception") })
	/*
	 * @param Fornecedor esse metodo salva um fornecedor e retorna ele
	 * 
	 * @return Fornecedor
	 */
	@PostMapping()
	public ResponseEntity<DtoFornecedor> saveFornecedores(@Validated @RequestBody DtoFornecedor fornecedores) {
		DtoFornecedor returnFornecedores = null;
		ModelMapper modelMapper = new ModelMapper();
		DtoFornecedor dto =  modelMapper.map(fornecedores, DtoFornecedor.class);
		returnFornecedores = this.service.insertFornecedores(dto);
		return new ResponseEntity<DtoFornecedor>(returnFornecedores, HttpStatus.CREATED);
	}
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Faz o update na classe por completo", response = DtoFornecedor.class, notes = "Essa operacao retorna o fornecedor salvo")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Retorna o fornecedor que foi feito o update", response = DtoFornecedor.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado o fornecedor que deseja atualizar sera lancado um exception") })
	/*
	 * @param Fornecedor esse metodo faz um update na classe fornecedor por completo
	 * 
	 * @return Fornecedor
	 */
	@PutMapping()
	public ResponseEntity<DtoFornecedor> updateFornecedores(@Validated @RequestBody DtoFornecedor fornecedores) {
		DtoFornecedor returnFornecedores = null;
		returnFornecedores = this.service.insertFornecedores(fornecedores);
		return new ResponseEntity<DtoFornecedor>(returnFornecedores, HttpStatus.NO_CONTENT);
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
	public ResponseEntity<?> ativarFornecedores(@Validated @PathVariable String name) {
		this.service.desativarOrAtivarFornecedor(name);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
