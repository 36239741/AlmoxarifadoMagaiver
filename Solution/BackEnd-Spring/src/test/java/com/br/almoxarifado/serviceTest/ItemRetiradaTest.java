package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.repository.ItemRepository;
import com.br.almoxarifado.repository.ItemRetiradaRepository;
import com.br.almoxarifado.service.ItemRetiradaService;
import com.br.almoxarifado.service.ItemService;

public class ItemRetiradaTest extends AbstractIntegrationTest{
	@Autowired
	private ItemRetiradaRepository itemRetiradaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemRetiradaService itemRetiradaService;
		
	@Autowired
	private ItemService itemService; 
	
	/*SALVA UMA RETIRADA*/
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/truncateItemRetirada.sql",
			"/dataset/item.sql",
			"/dataset/itemRetirada.sql",
	})
	@Test
	public void itemRetiradaSaveMustPass() {
		ItemRetirada itemRetirada = new ItemRetirada();
		Map<String, Object>  map = null;
		Item item =this.itemService.findByCodigo("1234567");
		itemRetirada.getListItem().add(item);
		itemRetirada.setLocalRetirada("Almoxarifado");
		itemRetirada.setQuantidade(10);
		map = this.itemRetiradaService.retirada(itemRetirada);
		ItemRetirada returnitemRetirada = null;
		returnitemRetirada = (ItemRetirada) map.get("itemRetirada");
		Assert.assertNotNull(returnitemRetirada);
		Assert.assertEquals(returnitemRetirada.getQuantidade(), itemRetirada.getQuantidade());
		Assert.assertEquals(returnitemRetirada.getLocalRetirada(), itemRetirada.getLocalRetirada());
		
	}
	/*TESTA O DELETE LOGICO DO ITEM RETIRADA*/
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/truncateItemRetirada.sql",
			"/dataset/item.sql",
			"/dataset/itemRetirada.sql",
	})
	@Test
	public void itemRetiradaDeleteLogicoMustPassTestaODeleteLogico() {
		this.itemRetiradaService.deleteLogico(21L);
		final Integer quantidade = 40 ;
		ItemRetirada itemRetirada = this.itemRetiradaRepository.findById(21L);
		Item item = this.itemRepository.findByCodigo("1234567");
		Assert.assertNotNull(item);
		Assert.assertNotNull(itemRetirada);
		Assert.assertEquals(Boolean.FALSE, itemRetirada.getRetiradaStatus());
		Assert.assertEquals(quantidade, item.getQuantidade());
		
	}
	
	
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/truncateItemRetirada.sql",
			"/dataset/item.sql",
			"/dataset/itemRetirada.sql",
	})
	@Test
	public void itemRetiradaFindAllMustPass() {
		Page<ItemRetirada> pageItemRetirada = null;
		pageItemRetirada = this.itemRetiradaService.findAll(0, 1);
		Assert.assertNotNull(pageItemRetirada);
		Assert.assertEquals(1, pageItemRetirada.getTotalElements());
	}
	
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/truncateItemRetirada.sql",
			"/dataset/item.sql",
			"/dataset/itemRetirada.sql",
	})
	@Test
	public void findByIdItemRetirada() {
		/*ATRIBUTOS	*/
		ItemRetirada returnItemRetirada = null;
		
		/*CONSTRUCAO*/
		returnItemRetirada = this.itemRetiradaService.findByIdItemRetirada(21);
						
		/*TESTE*/
		Assert.assertNotNull(returnItemRetirada);
		Assert.assertEquals(21, returnItemRetirada.getId());
	}
}
