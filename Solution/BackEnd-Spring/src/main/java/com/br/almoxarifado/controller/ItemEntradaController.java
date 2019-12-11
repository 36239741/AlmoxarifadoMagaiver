package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.ItemEntrada;
import com.br.almoxarifado.service.ItemEntradaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping(path = "v1/item_entrada")
public class ItemEntradaController {
	
	@Autowired
	private ItemEntradaService service;
	

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um item entrada", response = ItemEntrada.class, notes = "Essa operacao busca um item_entrada pelo id da entrada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna um item entrada com uma mensagem de sucesso", response = ItemEntrada.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado nenhum item entrada sera lancado um exception com o erro") })
	/*
	 * @param long id o metodo retorna o item entrada
	 * 
	 * @return o ItemEntrada Buscado
	 */
	@GetMapping(path = "/{entrada_id}")
	public ResponseEntity<ItemEntrada> findItemEntradaById(@Validated @PathVariable long entrada_id) {
		ItemEntrada itementrada = this.service.findByIdItemEntrada(entrada_id);
		return ResponseEntity.ok(itementrada);
	}
	

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Busca todos item_entrada", response = ItemEntrada.class, notes = "Essa operacao retorna uma Page de  todos os item_entrada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna uma lista de item_entrada com uma mensagem de sucesso", response = ItemEntrada.class),
			@ApiResponse(code = 400, message = "Caso nao for encontrado nenhum item_entrada sera lancado um exception com o erro") })
	/*
	 * @param Int page Int size esse metodo retorna todos os item_entrada atraves de
	 * uma Pageable
	 * 
	 * @return retorna uma page de item_entrada
	 */
	@GetMapping
	public ResponseEntity<Page<ItemEntrada>> findAll(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) {
		Page<ItemEntrada> pageItemEntrada = null;
		pageItemEntrada = this.service.findAll(page, size);
		return new ResponseEntity<>(pageItemEntrada, HttpStatus.OK);
	}
}
