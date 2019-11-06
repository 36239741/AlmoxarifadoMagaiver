package com.br.almoxarifado.service;


import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.dto.DtoItemReponse;
import com.br.almoxarifado.dto.DtoItemRequest;
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
	 * @return retornara o DtoItem persistido
	 */
	
	public DtoItemRequest insert(DtoItemRequest item) {
		Item findByIdItem = null;
		Item itemPersist = null;
		Item convertItem = this.convertDtoItem(item);
		
		findByIdItem = this.findByDescricao(item.getDescricao());
		if(findByIdItem != null) {
			if((findByIdItem.getFornecedor().getNome().equals(item.getFornecedor().getNome())) && (findByIdItem.getDescricao().equals(item.getDescricao()))) {
				throw new ExistingItemException("Estem item: "+ findByIdItem.getDescricao() +" ja esta cadastrado");
			}
			else {
				itemPersist =  this.gerarCodigoItem(convertItem);
			}
		}
		else {
			itemPersist = this.gerarCodigoItem(convertItem);
		}
			
		return this.convertItem(itemPersist);
	}

	/*
	 * servico de update
	 * @param Item realizara o update do item
	 * @return Item que foi modificado
	 */
	public DtoItemReponse update(Item item) {
		return this.convertDtoItem(item);
	}

	/**
	 * Serviço buscar um item pela descricao
	 * @param String descricao do item
	 * sera realizada uma busca pelo campo descricao
	 * @return Item
	 */
	
	private Item findByDescricao(String descricao) {
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
	public Page<DtoItemReponse> findAllIten(int page, int size){
	Page<Item> pageItens;
	PageRequest pageable = PageRequest.of(page,size);
	pageItens = this.repository.findAll(pageable);
	return this.convertPageItem(pageItens);
	}
	
	/**
	 * Serviço buscar um item pelo codigo
	 * @param  String codigo
	 * sera realizada uma busca pelo codigo
	 * @return Item
	 */
	public Item findByCodigo(String codigo) {
		Item returnItem = this.repository.findByCodigo(codigo);
		Assert.notNull(returnItem, "Codigo: "+codigo+" do item nao ecnotrado");
		return returnItem;
	}
	
	/*
	 * Servico para gerar um codigo alfanumerico randomico
	 * @param Item gera um codigo alfanumerico randomico com a biblioteca apache
	 * commons e vericaca a unicidade do codigo
	 * @return o item persistido
	 */	
	
	
	private Item gerarCodigoItem(Item item) {
		String codigo = null;
		Item itemPersist = null;

		do {
			codigo = RandomStringUtils.random(8, false,true);
			Item returnItem = this.repository.findByCodigo(codigo);
			if(returnItem == null) {
				item.setCodigo(codigo);
				itemPersist = this.repository.save(item);
			}
		
		}while(itemPersist == null);
		
		return itemPersist;
	}

	/*Servico para conveter um item
	 * @param item 
	 *  convert um item.class para um DtoItem.class
	 * @return DtoItem
	 */
	public DtoItemRequest convertItem(Item item) {
		ModelMapper modelMapper =new ModelMapper();
		return modelMapper.map(item, DtoItemRequest.class);
		
	}
	
	/*Servico para converte um dtoItem
	 * @param DtoItem  
	 * convert um DtoItem.class para um Item.class
	 * @return Item
	 */
	public Item convertDtoItem(DtoItemRequest dtoItem) {
		ModelMapper modelMapper =new ModelMapper();
		return modelMapper.map(dtoItem, Item.class);
		
	}
	
	/*Servico para converte um Item
	 * @param DtoItemResonse
	 * convert um Item.class para um DtoItemReponse.class
	 * @return DtoItemResponse
	 */
	public DtoItemReponse convertDtoItem(Item item) {
		ModelMapper modelMapper =new ModelMapper();
		return modelMapper.map(item, DtoItemReponse.class);
		
	}
	
	/*Servico para converte um dtoItem
	 * @param DtoItemReponse  
	 * convert um DtoItem.class para um DtoItemReponse.class
	 * @return DtoItemReponse
	 */
	public DtoItemReponse convertDtoItemResponse(DtoItemRequest dtoItem) {
		ModelMapper modelMapper =new ModelMapper();
		return modelMapper.map(dtoItem, DtoItemReponse.class);
		
	}
	
	
	/*Servico para converte um Page<DtoItemReponse>
	 * @param Page<Item>  
	 * convert um Page<Item>  para um Page<DtoItemReponse>
	 * @return Page<DtoItemReponse>
	 */
	public Page<DtoItemReponse> convertPageItem(Page<Item> item) {
		ModelMapper modelMapper =new ModelMapper();
		java.lang.reflect.Type targetListType = new TypeToken<Page<DtoItemReponse>>() {}.getType();
		return modelMapper.map(item, targetListType);
		
	}
	
	

	

}
