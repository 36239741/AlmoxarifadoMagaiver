package com.br.almoxarifado.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.entity.Servico;
import com.br.almoxarifado.repository.ItemRetiradaRepository;

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

	@Autowired
	private ItemService itemService;

	/*
	 * @param ItemRetirada O metodo salva um item retirada , com sua lista de itens
	 * 
	 * @return ItemRetirada
	 */
	public Map<String, Object> retirada(ItemRetirada itemRetirada) {
		List<Item> returnItem = null;
		Double valorTotalRetirada = 0.0;
		Map<String, Object> map = new HashMap<>();
		returnItem = this.itemService.atualizaEstoque(itemRetirada, Servico.RETIRADA);
		for (Item item : itemRetirada.getListItem()) {
			valorTotalRetirada += item.getValor();
		}
		itemRetirada.setValor(valorTotalRetirada);
		ItemRetirada returnItemRetirada = this.repository.save(itemRetirada);
		if (returnItemRetirada != null) {
			map.put("itemRetirada", returnItemRetirada);
			map.put("itemSemEstoque", returnItem);
		}
		return map;
	}

	public Page<ItemRetirada> findAllItemRetirada(int page, int pageSize) {
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return new PageImpl<>(this.repository.findAllItemRetirada(), pageRequest, pageSize);
	}
	
	public Page<ItemRetirada> findAFilters(int page, int pageSize, Long retiradaId, String quemRetirou) {
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		

		return this.repository.findByFilters(retiradaId,quemRetirou, pageRequest);
	}

	/*
	 * @param long id do item_retirada buscar o item_retirada pelo retirada_id
	 * 
	 * @return ItemRetirada
	 */
	@Transactional(readOnly = true)
	public ItemRetirada findByIdItemRetirada(long id) {
		ItemRetirada returnItemRetirada = this.repository.findById(id);
		Assert.notNull(returnItemRetirada, "Item da retirada " + id + " nao encontrado");
		return returnItemRetirada;
	}

	/*
	 * @param integer page, integer pageSize ira fazer um find all e retornara em
	 * pageable
	 * 
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

	/*
	 * Servico que faz um delete logico na entidade ItemRetirada
	 * 
	 * @Param itemRetiradaId Long - recebe o id da retirada
	 * 
	 * @return void
	 */
	public void deleteLogico(Long itemRetiradaId) {
		ItemRetirada itemRetirada = this.repository.findById(itemRetiradaId).get();
		this.itemService.atualizaEstoque(itemRetirada, Servico.ENTRADA);
		this.repository.deleteLogico(itemRetiradaId);
	}

}
