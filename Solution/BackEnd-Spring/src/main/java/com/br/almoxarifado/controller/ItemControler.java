package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.service.ItemService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/itens")
public class ItemControler {
	@Autowired
	private ItemService service;

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Salva um item", response = Item.class, notes = "Essa operacao salva um item")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "retorna o item salvo", response = Item.class),
			@ApiResponse(code = 400, message = "Caso ocorra algum error no request sera lancado uma exception") })
	/*
	 * @param Item o metodo salva um item
	 * 
	 * @return retorna o item salvo
	 */
	@PostMapping
	public ResponseEntity<Item> savetitem(@Validated @RequestBody Item item) {
		Item returnItem = this.service.saveItem(item);
		return new ResponseEntity<>(returnItem, HttpStatus.CREATED);
	}

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um item pelo codigo", response = Item.class, notes = "Essa operacao busca um item pelo codigo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna o item buscado", response = Item.class),
			@ApiResponse(code = 400, message = "Caso ocorra algum error no request sera lancado uma exception") })

	/*
	 * @param String codig o metodo busca um item pel0 codigo
	 * 
	 * @return retorna o item buscado
	 */
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<Item> getBiyCodigo(@PathVariable String codigo) {
		Item item = this.service.findByCodigo(codigo);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping(path = "/item-filter")
	public ResponseEntity<Page<Item>> findByFilter(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("codigo") String codigo , @RequestParam("descricao") String descricao ,
			@RequestParam("localArmazenamento") String localArmazenamento) {
		PageRequest pageable = PageRequest.of(page, size);
		Page<Item> item = this.service.itemFilter(codigo, descricao, localArmazenamento, pageable);
		return ResponseEntity.ok(item);
	}


	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca todos itens cadastrados", response = Item.class, notes = "Essa operacao trazera uma List de itens")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna uma List de Itens", response = Item.class),
			@ApiResponse(code = 400, message = "Caso ocorra algum error no request sera lancado uma exception") })
	/*
	 * @param int page, int size o metodo retornara uma list de itens
	 * 
	 * @return retorna uma List<DtoItem>
	 */

	@GetMapping()
	public ResponseEntity<Page<Item>> findAll(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "size", required = true) int size) {
		Page<Item> returnItem = null;
		returnItem = this.service.findAllItem(page, size);
		return new ResponseEntity<>(returnItem, HttpStatus.OK);

	}

	@PatchMapping()
	public ResponseEntity<Item> updateItem(@RequestBody Item item) {
		Item reutnrItem = null;
		reutnrItem = this.service.saveItem(item);
		return new ResponseEntity<>(reutnrItem, HttpStatus.NO_CONTENT);
	}

}
