package com.br.almoxarifado.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemEntrada;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.entity.Servico;
import com.br.almoxarifado.repository.ItemEntradaRepository;

@Service
@Transactional
public class ItemEntradaService {
	@Autowired
	private ItemEntradaRepository repository;
	
	private ItemService itemService;
	
	public Map<String, Object> entrada(ItemEntrada itemEntrada) {
		List<Item> returnItem = null;
		Double valorTotalEntrada = 0.0;
		Map<String, Object> map = new HashMap<>();
		returnItem = this.itemService.atualizaEstoqueEntrada(itemEntrada, Servico.ENTRADA);
		for (Item item : itemEntrada.getListItem()) {
			valorTotalEntrada += item.getValor();
		}
		itemEntrada.setValor(valorTotalEntrada);
		ItemEntrada returnItemEntrada = this.repository.save(itemEntrada);
		if (returnItemEntrada != null) {
			map.put("itemEntrada", returnItemEntrada);
		}
		return map;
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
	
	/*
	 * Servico que faz um delete logico na entidade ItemEntrada
	 * 
	 * @Param itemEntradaId Long - recebe o id da entrada
	 * 
	 * @return void
	 */
	public void deleteLogico(Long itemEntradaId) {
		ItemEntrada itemEntrada = this.repository.findById(itemEntradaId).get();
		this.itemService.atualizaEstoqueEntrada(itemEntrada, Servico.ENTRADA);
		this.repository.deleteLogico(itemEntradaId);
	}
}
