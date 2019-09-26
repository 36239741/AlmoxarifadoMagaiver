package com.br.almoxarifado.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.error.ExistingItemException;
import com.br.almoxarifado.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	@Autowired
	private ItemRepository repository;


	/**
	 * Serviço para inserir um item 
	 * @param item
	 * @return
	 */
	
	public Item insert(Item item) {
		Item findByIdItem = null;
		Item itemPersist = null;
		findByIdItem = this.repository.findByDescricao(item.getDescricao());
		if(findByIdItem != null) {
			if(findByIdItem.getFornecedor().getFornecedorId().equals(item.getFornecedor().getFornecedorId()) && findByIdItem.getDescricao().equals(item.getDescricao())) {
				throw new ExistingItemException("Estem item: "+ findByIdItem.getDescricao() +" ja esta cadastrado");
			}
			else {
				itemPersist =  this.repository.save(item);
			}
		}
		else {
			itemPersist = this.repository.save(item);
		}
		return itemPersist;
	}

	/**
	 * Serviço buscar um item pela descricao
	 * @param descricao
	 * @return
	 */
	
	public Item findByDescricao(String descricao) {
		Item findItem = null;
		findItem = this.repository.findByDescricao(descricao);
		Assert.notNull(findItem, "Item com a descricao: "+descricao+" nao encontrado");
		return findItem;
	}



	/**
	 * Serviço buscar um item pelo id
	 * @param itemId
	 * @return
	 */
	
	public Item findById(long itemId) {
		Item itemFindById = null;
		itemFindById = this.repository.findById(itemId);
		Assert.notNull(itemFindById, "ID: "+itemId+" nao encontrado");
		return itemFindById;

	}
	

	/**
	 * Serviço buscar um item pelo id
	 * @param localArmazenamento
	 * @return
	 */
	
	public Item findByLocalDeArmazenamento(String localArmazenamento) {
		Item item = this.repository.findByLocalArmazenamento(localArmazenamento);
		Assert.notNull(item, "Local de armazenamento: "+localArmazenamento+" nao encontrado");
		return item ;
	}

	

	

}
