package com.br.almoxarifado.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.service.ItemRetiradaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping(path = "v1/item_retirada")
public class ItemRetiradaController {
	
	@Autowired
	private ItemRetiradaService service;

	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um item retirada", response = ItemRetirada.class, notes = "Essa operacao busca um item_retirada pelo id da retirada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna um item retirada com uma mensagem de sucesso", response = ItemRetirada.class),
			@ApiResponse(code = 500, message = "Caso nao for encontrado nenhum item retirada sera lancado um exception com o erro") })
	/*
	 * @param long id o metodo retorna o item retirada
	 * 
	 * @return o ItemRetirada Buscado
	 */
	@GetMapping(path = "/{retirada_id}")
	public ResponseEntity<ItemRetirada> findItemRetiradaById(@Validated @PathVariable long retirada_id) {
		ItemRetirada itemretirada = this.service.findByIdItemRetirada(retirada_id);
		return ResponseEntity.ok(itemretirada);
	}
	
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */

	@ApiOperation(value = "Busca todos item_retirada", response = ItemRetirada.class, notes = "Essa operacao retorna uma Page de  todos os item_retiradas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna uma lista de item_retirada com uma mensagem de sucesso", response = ItemRetirada.class),
			@ApiResponse(code = 400, message = "Caso nao for encontrado nenhum item_retirada sera lancado um exception com o erro") })
	/*
	 * @param Int page Int size esse metodo retorna todos os item_retirada atraves de
	 * uma Pageable
	 * 
	 * @return retorna uma page de item_retirada
	 */
	@GetMapping
	public ResponseEntity<Page<ItemRetirada>> findAll(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) {
		Page<ItemRetirada> pageItemRetirada = null;
		pageItemRetirada = this.service.findAll(page, size);
		return new ResponseEntity<>(pageItemRetirada, HttpStatus.OK);
	}
	
	@PostMapping(path = "/retirar")
	public void saveItemRetirada(@RequestBody Map<String , Object> itemRetirada) {
		ItemRetirada objectItemRetirada = new ItemRetirada();
		ObjectMapper object =new ObjectMapper();
		List<Item> listItem = (List<Item>) itemRetirada.get("listItem"); 
		for(int i =0; i < listItem.size(); i++) {
			objectItemRetirada.getListItem().add(object.convertValue(listItem.get(i), Item.class));
		}
		objectItemRetirada.setLocalRetirada(itemRetirada.get("locaRetirada").toString());
		objectItemRetirada.setQuemRetirou(itemRetirada.get("quemRetirou").toString());
		this.service.retirada(objectItemRetirada);
		
	}
}
