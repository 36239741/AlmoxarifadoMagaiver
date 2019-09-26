package com.br.almoxarifado.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.util.List;

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

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.service.FornecedoresService;

@RestController()
@RequestMapping(path = "v1/fornecedores")
public class FornecedoresController {
	@Autowired
	private FornecedoresService service;
	
	@GetMapping(path = "/{name}")
	public ResponseEntity<Fornecedores> findFonecedoresByName(@Validated @PathVariable String name){
		Fornecedores fornecedores = null;
		fornecedores = this.service.findByNomeFornecedor(name);
		return new ResponseEntity<Fornecedores>(fornecedores,HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Fornecedores>> findAll(@RequestParam(value = "page",required = true) Integer page , @RequestParam(value = "size",required = true) Integer size){
		Page<Fornecedores> resultPage = null;		
		resultPage = this.service.findAll(page,size);
		for(Fornecedores forn : resultPage.getContent()) {
			String nome = forn.getNome();
			forn.add(linkTo(methodOn(FornecedoresController.class).findFonecedoresByName(nome)).withSelfRel().withRel("Find by name").withType("GET"));
			forn.add(linkTo(methodOn(FornecedoresController.class).ativarFornecedores(nome)).withSelfRel().withRel("Active or desative by name").withType("PATCH"));

		}
		return new ResponseEntity<List<Fornecedores>>(resultPage.getContent(),HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<Fornecedores> saveFornecedores(@Validated @RequestBody Fornecedores fornecedores) throws URISyntaxException{
		Fornecedores returnFornecedores = null;
		returnFornecedores =this.service.insertFornecedores(fornecedores);
		returnFornecedores.add(linkTo(methodOn(FornecedoresController.class).findFonecedoresByName(returnFornecedores.getNome())).withSelfRel().withRel("Find by name").withType("GET"));
		return new ResponseEntity<Fornecedores>(returnFornecedores,HttpStatus.CREATED);
	}
	@PutMapping()
	public ResponseEntity<Fornecedores> updateFornecedores(@Validated @RequestBody Fornecedores fornecedores){
		Fornecedores returnFornecedores = null;
		return new ResponseEntity<Fornecedores>(returnFornecedores,HttpStatus.NO_CONTENT);	
	}
	@PatchMapping(path = "/{name}")
	public ResponseEntity<?> ativarFornecedores(@Validated @PathVariable String name){
		 this.service.desativarOrAtivarFornecedor(name);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
