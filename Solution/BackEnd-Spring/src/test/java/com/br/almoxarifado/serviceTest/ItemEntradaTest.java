package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemEntrada;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.repository.ItemEntradaRepository;
import com.br.almoxarifado.repository.ItemRepository;
import com.br.almoxarifado.repository.ItemRetiradaRepository;
import com.br.almoxarifado.service.ItemEntradaService;
import com.br.almoxarifado.service.ItemService;

public class ItemEntradaTest extends AbstractIntegrationTest {
	@Autowired
	private ItemEntradaService itemEntradaService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemEntradaRepository itemEntradaRepository;
	
	@Autowired
	private ItemService itemService;
	
	
	
												/*SALVA UMA ENTRADA*/
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
<<<<<<< HEAD
			"/dataset/item.sql",
=======
			"/dataset/truncateItemEntrada.sql",
			"/dataset/item.sql",
			"/dataset/itemEntrada.sql",
>>>>>>> b41dcedf7b27041a6f5e13e76f5359ef47d5bb9c
	})
	@Test
	public void itemEntradaSaveMustPass() {
		ItemEntrada itemEntrada = new ItemEntrada();
<<<<<<< HEAD
		ItemEntrada returnItemEntrada = null;
		Item item = this.itemService.findByCodigo("1234567");
		List<Item> listItem = new ArrayList<>();
		listItem.add(item);
		itemEntrada.setListItem(listItem);
=======
		Map<String, Object>  map = null;
	
		Item item = this.itemService.findByCodigo("1234567");
	
		itemEntrada.getListItem().add(item);
		
>>>>>>> b41dcedf7b27041a6f5e13e76f5359ef47d5bb9c
		itemEntrada.setLocalEntrada("Almoxarifado");
		itemEntrada.setQuantidade(5);
		itemEntrada.setValor(30.00);
		
		map = this.itemEntradaService.entrada(itemEntrada);
		ItemEntrada returnItemEntrada = null;
		returnItemEntrada = (ItemEntrada) map.get("itemEntrada");
		Assert.assertNotNull(returnItemEntrada);
		Assert.assertEquals(returnItemEntrada.getQuantidade(), itemEntrada.getQuantidade());
		Assert.assertEquals(returnItemEntrada.getLocalEntrada(), itemEntrada.getLocalEntrada());
		
	}
	
	@Sql(scripts = {
			"/dataset/truncateItemEntrada.sql",
			"/dataset/itemEntrada.sql",
	})
	@Test
	public void itemEntradaFindAllMustPass() {
		Page<ItemEntrada> pageItemEntrada = null;
		pageItemEntrada = this.itemEntradaService.findAll(0, 10);
		Assert.assertNotNull(pageItemEntrada);
		Assert.assertEquals(1, pageItemEntrada.getTotalElements());
	}
	
	@Sql(scripts = {
			"/dataset/truncateItemEntrada.sql",
			"/dataset/itemEntrada.sql",
	})
	@Test
	public void findByIdItemEntrada() {

		ItemEntrada returnItemEntrada = null;
		
		returnItemEntrada = this.itemEntradaService.findByIdItemEntrada(21);
						
		System.out.println(returnItemEntrada);
		Assert.assertNotNull(returnItemEntrada);
		Assert.assertEquals(21, returnItemEntrada.getId());
	}
	
						/*TESTA O DELETE LOGICO DO ITEM Entrada*/
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/truncateItemEntrada.sql",
	"/dataset/item.sql",
	"/dataset/itemEntrada.sql",
	})
	@Test
	public void itemEntradaDeleteLogicoMustPassTestaODeleteLogico() {
		this.itemEntradaService.deleteLogico(21L);
		final Integer quantidade = 40 ;
		ItemEntrada itemEntrada = this.itemEntradaRepository.findById(21L);
		System.out.println("Item Entrada: " + itemEntrada);
		Item item = this.itemRepository.findByCodigo("1234567");
		System.out.println("Item : " + item.getQuantidade());
		
		
		
		Assert.assertNotNull(itemEntrada);
		Assert.assertEquals(quantidade, item.getQuantidade());

	}

}
