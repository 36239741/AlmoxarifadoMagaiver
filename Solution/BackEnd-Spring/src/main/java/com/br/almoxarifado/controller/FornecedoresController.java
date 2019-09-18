package com.br.almoxarifado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.service.FornecedoresService;

@RestController()
@RequestMapping(path = "/fornecedores")
public class FornecedoresController {
	@Autowired
	private FornecedoresService service;
	
	@GetMapping(path = "/findByName/{name}")
	public ResponseEntity<?> findFonecedoresByName(@PathVariable String name){
		this.service.verifyFornecedores(name);
		return new ResponseEntity<>(this.service.findByNomeFornecedor(name),HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<?> saveFornecedores(@Validated @RequestBody Fornecedores fornecedores){
		this.service.insertFornecedores(fornecedores);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping()
	public ResponseEntity<?> updateFornecedores(@Validated @RequestBody Fornecedores fornecedores){
		this.service.insertFornecedores(fornecedores);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping()
	public ResponseEntity<?> deleteFornecedores(@Validated @RequestBody Fornecedores fornecedores){
		this.service.insertFornecedores(fornecedores);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
