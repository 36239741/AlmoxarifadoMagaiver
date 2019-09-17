package com.br.almoxarifado.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	@Autowired
	private ItemRepository repository;

	// O item so vai ser inserido se nao tiver um mesmo item com o mesmo fornecedor
	public Item insert(Item item) {
		Item itemPersistido = null;
		Item itemFindByDescricao = null;
		itemFindByDescricao = this.findByDescricao(item.getDescricao());
		if (itemFindByDescricao != null) {
			if (itemFindByDescricao.getFornecedor().getId() != item.getFornecedor().getId()) {
				itemPersistido = this.repository.save(item);
			}
		} else {
			itemPersistido = this.repository.save(item);
		}
		return itemPersistido;
	}

	// Busca pelo campo descricao
	public Item findByDescricao(String descricao) {
		Item findItem = null;
		findItem = this.repository.findByDescricao(descricao);
		return findItem;
	}

	// Edita o item somente se achar ele no banco
	public Item edit(Item item) {
		Item findByIditem = null;
		Item editedItem = null;
		findByIditem = this.findById(item.getId());
		if (findByIditem != null) {
			editedItem = this.repository.save(item);
		}
		return editedItem;
	}

	// Busca o item pelo Id
	public Item findById(Long itemId) {
		Item itemFindById = null;
		itemFindById = this.repository.FindById(itemId);
		return itemFindById;

	}

}
