package com.br.almoxarifado.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.entity.Servico;
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
	 * 
	 * @param item sera verificado se ja existe um item com a descricao informada se
	 *             houver retorno sera verifcado se o fornecedor e a descricao sao
	 *             iguais se for sera lancado um exception ,se nao sera persistido
	 *             no banco, caso nao haja retorno sera persistido no banco.
	 * @return retornara o DtoItem persistido
	 */

	public Item saveItem(Item item) {
		Item findByIdItem = null;
		Item itemPersist = null;

		findByIdItem = this.findByDescricao(item.getDescricao());
		if (findByIdItem != null) {
			if ((findByIdItem.getFornecedor().getNome().equals(item.getFornecedor().getNome()))
					&& (findByIdItem.getDescricao().equals(item.getDescricao()))) {
				throw new ExistingItemException("Estem item: " + findByIdItem.getDescricao() + " ja esta cadastrado");
			} else {
				itemPersist = this.gerarCodigoItem(item);
			}
		} else {
			itemPersist = this.gerarCodigoItem(item);
		}

		return itemPersist;
	}

	/**
	 * Serviço buscar um item pela descricao
	 * 
	 * @param String descricao do item sera realizada uma busca pelo campo descricao
	 * @return Item
	 */

	private Item findByDescricao(String descricao) {
		Item findItem = null;
		findItem = this.repository.findByDescricaoIgnoreCase(descricao);
		return findItem;
	}

	/**
	 * Serviço buscar todos itens salvos
	 * 
	 * @param int page int size sera retornado todos os itens em formato de pageable
	 * @return Item
	 */
	@Transactional(readOnly = true)
	public Page<Item> findAllItem(int page, int size) {
		Page<Item> pageItens = null;
		PageRequest pageable = PageRequest.of(page, size);
		pageItens = this.repository.findAll(pageable);
		pageItens.getContent().forEach(item -> {
			item.getFornecedor().getItem().clear();
		});
		return pageItens;
	}

	/**
	 * Serviço buscar um item pelo codigo
	 * 
	 * @param String codigo sera realizada uma busca pelo codigo
	 * @return Item
	 */
	public Item findByCodigo(String codigo) {
		Item returnItem = this.repository.findByCodigo(codigo);
		Assert.notNull(returnItem, "Codigo: " + codigo + " do item nao encontrado");
		return returnItem;
	}

	/*
	 * Servico para gerar um codigo alfanumerico randomico
	 * 
	 * @param Item gera um codigo alfanumerico randomico com a biblioteca apache
	 * commons e vericaca a unicidade do codigo
	 * 
	 * @return o item persistido
	 */

	private Item gerarCodigoItem(Item item) {
		String codigo = null;
		Item itemPersist = null;

		do {
			codigo = RandomStringUtils.random(8, false, true);
			Item returnItem = this.repository.findByCodigo(codigo);
			if (returnItem == null) {
				item.setCodigo(codigo);
				itemPersist = this.repository.save(item);
			}

		} while (itemPersist == null);

		return itemPersist;
	}

	public Page<Item> itemFilter(String codigo, String descricao, String localArmazenamento, PageRequest pageable) {
		return this.repository.findByFilters(codigo, descricao, localArmazenamento, pageable);
	}

	/*
	 * Servico para atualizar o estoque dos itens baseado na list de item retirada
	 * 
	 * @Param list List<Item> - list de itens a serem retirados
	 * 
	 * @Return List<Item> - retorna os itens sem estoque para retirada
	 */
	public List<Item> atualizaEstoque(ItemRetirada itemRetirada, Servico servico) {
		List<Item> itemSemEstoque = new ArrayList<Item>();
		for (Item item : itemRetirada.getListItem()) {
			Item returnItem = null;
			returnItem = this.findByCodigo(item.getCodigo());
			if(servico.equals(Servico.RETIRADA)) {
				if (returnItem.getQuantidade() > itemRetirada.getQuantidade()) {
					returnItem.setQuantidade(returnItem.getQuantidade() - item.getQuantidade());
				} else {
					itemSemEstoque.add(item);
				}
			}
			else {		
					returnItem.setQuantidade(returnItem.getQuantidade() + item.getQuantidade());
			}

			this.repository.save(returnItem);
		}

		return itemSemEstoque;	

	}

}
