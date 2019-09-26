package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.service.ItemService;

@RestController
@RequestMapping(value = "/v1/items")
public class ItemControler {
	@Autowired
	private ItemService service;
	
	@PostMapping
	public ResponseEntity<Item> insertitem(@Validated @RequestBody Item item){
		Item returnItem = this.service.insert(item);
		return new ResponseEntity<Item>(returnItem, HttpStatus.CREATED);
	}
	@GetMapping(path = "/{descricao}")
	public ResponseEntity<Item> getBiyDescricao(@PathVariable String descricao){
		Item item = this.service.findByDescricao(descricao);
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
}
