package com.br.almoxarifado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.ItemEntrada;
import com.br.almoxarifado.repository.ItemEntradaRepository;

@Service
@Transactional
public class ItemEntradaService {
	@Autowired
	private ItemEntradaRepository repository;
	
	private ItemService itemService;
	
	public ItemEntrada entrada(ItemEntrada itemEntrada) {
		ItemEntrada returnItemEntrada = null;
		
		returnItemEntrada = this.repository.save(itemEntrada);
		return returnItemEntrada;
	}
	
	public Page<ItemEntrada> findAll(int page, int pageSize){
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return this.repository.findAll(pageRequest);
	}
	
	/*
	 * @param long id do item_entrada
	 * buscar o item_entrada pelo entrada_id
	 * @return ItemEntrada
	 */
	@Transactional(readOnly = true)
	public ItemEntrada findByIdItemEntrada(long id) {
		ItemEntrada returnItemEntrada = this.repository.findById(id);
		Assert.notNull(returnItemEntrada, "Item da entrada " + id + " nao encontrado");
		return returnItemEntrada;
	}
	
	/*
	 * @param integer page, integer pageSize
	 * ira fazer um find all e retornara em pageable
	 * @return page item_entrada
	 */		
	@Transactional(readOnly = true)
	public Page<ItemEntrada> findAll(Integer page, Integer pageSize) {
		Page<ItemEntrada> pageItemEntrada;
		PageRequest pageable = PageRequest.of(page, pageSize);
		pageItemEntrada = this.repository.findAll(pageable);
		pageItemEntrada.getContent().forEach(data -> data.listItemClear());
		return pageItemEntrada;
	}
}
