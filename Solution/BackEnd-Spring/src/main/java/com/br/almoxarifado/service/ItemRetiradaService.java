package com.br.almoxarifado.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.repository.ItemRetiradaRepository;

import antlr.collections.List;

/*-------------------------------------------------------------------
 * 		 				ESCOPO DE FUNCAO
 *-------------------------------------------------------------------*/
/*-------------------------------------------------------------------
 * 		 					VARIAVEIS
 *-------------------------------------------------------------------*/

/*-------------------------------------------------------------------
 * 		 					LOGICA
 *-------------------------------------------------------------------*/

/*-------------------------------------------------------------------
 * 		 					RETURN
 *-------------------------------------------------------------------*/

@Service
@Transactional
public class ItemRetiradaService {
	@Autowired
	private ItemRetiradaRepository repository;
	

	private ItemService itemService;
	/*
	 * @param ItemRetirada 
	 * O metodo salva um item retirada , com sua lista de itens
	 * @return ItemRetirada
	 */
	public ItemRetirada retirada(ItemRetirada itemRetirada){
			
			ItemRetirada returnItemRetirada = null;
			
			returnItemRetirada = this.repository.save(itemRetirada);
			System.out.println("Item retirada: " + returnItemRetirada);			
			if(returnItemRetirada != null) {
				this.itemService.atualizaEstoque("1234567", "saida", returnItemRetirada.getQuantidade());
			}
			
			return returnItemRetirada;
	}
	
	public Page<ItemRetirada> findAll(int page, int pageSize) {
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return this.repository.findAll(pageRequest);
	}
	

	/*
	 * @param long id do item_retirada
	 * buscar o item_retirada pelo retirada_id
	 * @return ItemRetirada
	 */
	@Transactional(readOnly = true)
	public ItemRetirada findByIdItemRetirada(long id) {
		ItemRetirada returnItemRetirada = this.repository.findById(id);
		Assert.notNull(returnItemRetirada, "Item da retirada " + id + " nao encontrado");
		return returnItemRetirada;
	}
	
	/*
	 * @param integer page, integer pageSize
	 * ira fazer um find all e retornara em pageable
	 * @return page item_retirada
	 */		
	@Transactional(readOnly = true)
	public Page<ItemRetirada> findAll(Integer page, Integer pageSize) {
		Page<ItemRetirada> pageItemRetirada;
		PageRequest pageable = PageRequest.of(page, pageSize);
		pageItemRetirada = this.repository.findAll(pageable);
		pageItemRetirada.getContent().forEach(data -> data.listItemClear());
		return pageItemRetirada;
	}
	
}
