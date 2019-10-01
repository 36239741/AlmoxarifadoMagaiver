package com.br.almoxarifado.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.dto.DtoItem;
import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.error.ExistingItemException;
import com.br.almoxarifado.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
			
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
	
	
	@Autowired
	private ItemRepository repository;

	/**
	 * Serviço para inserir um item 
	 * @param item
	 * sera verificado se ja existe um item com a descricao informada se houver retorno sera verifcado se
	 * o fornecedor e a descricao sao iguais se for sera lancado um exception ,se nao sera persistido no banco,
	 * caso nao haja retorno sera persistido no banco.
	 * @return retornara o item persistido
	 */
	
	public DtoItem insert(DtoItem item) {
		Item findByIdItem = null;
		Item itemPersist = null;
		String codigo = null;
		Item convertItem = this.convertDtoItem(item);
		findByIdItem = this.findByDescricao(item.getDescricao());
		if(findByIdItem != null) {
			if(findByIdItem.getFornecedor().getFornecedorId() == convertItem.getFornecedor().getFornecedorId() && findByIdItem.getDescricao().equals(item.getDescricao())) {
				throw new ExistingItemException("Estem item: "+ findByIdItem.getDescricao() +" ja esta cadastrado");
			}
			else {
				itemPersist =  this.repository.save(convertItem);
			}
		}
		else {
			do {
				codigo = new GeradorDeCaracteresAleatorios().gerarCaractereAleatorio();
				Item returnItem = this.repository.findByCodigo(codigo);
				if(returnItem == null) {
					item.setCodigo(codigo);
					itemPersist = this.repository.save(convertItem);
				}
			
			}while(itemPersist == null);
		}
			
		return this.convertItem(itemPersist);
	}

	/*
	 * servico de update
	 * @param Item realizara o update do item
	 * @return Item que foi modificado
	 */
	public Item update(Item item) {
		return this.repository.save(item);
	}

	/**
	 * Serviço buscar um item pela descricao
	 * @param String descricao do item
	 * sera realizada uma busca pelo campo descricao
	 * @return Item
	 */
	
	public Item findByDescricao(String descricao) {
		Item findItem = null;
		findItem = this.repository.findByDescricaoIgnoreCase(descricao);
		return findItem;
	}


	
	/**
	 * Serviço buscar todos itens salvos
	 * @param  int page int size
	 * sera retornado todos os itens em formato de pageable
	 * @return Item
	 */
	@Transactional(readOnly = true)
	public Page<Item> findAllIten(int page, int size){
	Page<Item> pageItens;
	PageRequest pageable = PageRequest.of(page,size);
	pageItens = this.repository.findAll(pageable);
	return pageItens;
	}
	
	/**
	 * Serviço buscar um item pelo codigo
	 * @param  String localArmazenamento
	 * sera realizada uma busca pelo codigo
	 * @return Item
	 */
	public Item findByCodigo(String codigo) {
		Item returnItem = this.repository.findByCodigo(codigo);
		Assert.notNull(returnItem, "Codigo: "+codigo+" do item nao ecnotrado");
		return returnItem;
	}
	
	public DtoItem convertItem(Item item) {
		ModelMapper modelMapper =new ModelMapper();
		DtoItem dto = modelMapper.map(item, DtoItem.class);
		return dto;
	}
	public Item convertDtoItem(DtoItem dtoItem) {
		ModelMapper modelMapper =new ModelMapper();
		Item item = modelMapper.map(dtoItem, Item.class);
		return item;
	}
	

	

}
