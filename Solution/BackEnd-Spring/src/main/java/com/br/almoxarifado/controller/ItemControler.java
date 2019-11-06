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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.almoxarifado.dto.DtoItemReponse;
import com.br.almoxarifado.dto.DtoItemRequest;
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
	@ApiOperation(value = "Salva um item",
			response = DtoItemRequest.class,
			notes = "Essa operacao salva um item")
	@ApiResponses(value = {
			@ApiResponse(code = 201,
						message = "retorna o item salvo",
						response = DtoItemRequest.class),
			@ApiResponse(
						code = 400,
						message= "Caso ocorra algum error no request sera lancado uma exception")
	})
	/*
	 * @param Item
	 * o metodo salva um item
	 * @return retorna o item salvo
	 */
	@PostMapping
	public ResponseEntity<DtoItemRequest> insertitem(@Validated @RequestBody DtoItemRequest item){
		DtoItemRequest returnItem = this.service.insert(item);
		return new ResponseEntity<>(returnItem, HttpStatus.CREATED);
	}
	
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca um item pelo codigo",
			response = Item.class,
			notes = "Essa operacao busca um item pelo codigo")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
						message = "retorna o item buscado",
						response = Item.class),
			@ApiResponse(
						code = 400,
						message= "Caso ocorra algum error no request sera lancado uma exception")
	})
	
	/*
	 * @param String codig
	 * o metodo busca um item pel0 codigo
	 * @return retorna o item buscado
	 */
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<DtoItemRequest> getBiyCodigo(@PathVariable String codigo){
		Item item = this.service.findByCodigo(codigo);
		return new ResponseEntity<>(this.service.convertItem(item), HttpStatus.OK);
	}
	
	
	/*
	 * CONFIGURACOES DE DOCUMENTACAO DO SWAGGER
	 */
	@ApiOperation(value = "Busca todos itens cadastrados",
			response = Item.class,
			notes = "Essa operacao trazera uma List de itens")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
						message = "retorna uma List de Itens",
						response = Item.class),
			@ApiResponse(
						code = 400,
						message= "Caso ocorra algum error no request sera lancado uma exception")
	})
	/*
	 * @param int page, int size
	 * o metodo retornara uma list de itens
	 * @return retorna uma List<DtoItem>
	 */
	
	@GetMapping()
	public ResponseEntity<List<DtoItemReponse>> findAll(@RequestParam(value = "page",required = true) int page ,
			@RequestParam(value = "size",required = true) int size){
		Page<DtoItemReponse> returnItem = null;
		List<DtoItemReponse> dto = null;
		returnItem = this.service.findAllIten(page, size);
		ModelMapper modelMapper = new ModelMapper();
		java.lang.reflect.Type targetListType = new TypeToken<List<DtoItemReponse>>() {}.getType();
		dto = modelMapper.map(returnItem.getContent(), targetListType);
		for(DtoItemReponse item: dto) {
			String codigo =  item.getCodigo();
			item.add(linkTo(methodOn(ItemControler.class).getBiyCodigo(codigo)).withSelfRel().withRel("Find by codigo").withType("GET"));
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
		
	}
	/* FALTA ADIONAR NO SWAGGER , HATEOS E TESTE DE INTEGRACAO */
	
	@PatchMapping()
	public ResponseEntity<DtoItemReponse> updateItem(@RequestBody DtoItemRequest dtoRequest){
		DtoItemReponse dtoItemResponse = null;
		dtoItemResponse = this.service.update(this.service.convertDtoItem(dtoRequest));
		return new ResponseEntity<>(dtoItemResponse,HttpStatus.NO_CONTENT);
	}
	
	
}
