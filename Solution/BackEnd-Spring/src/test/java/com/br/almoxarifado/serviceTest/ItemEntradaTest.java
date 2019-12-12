package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemEntrada;
import com.br.almoxarifado.service.ItemEntradaService;
import com.br.almoxarifado.service.ItemService;

public class ItemEntradaTest extends AbstractIntegrationTest {
	@Autowired
	private ItemEntradaService itemEntradaService;
	
	@Autowired
	private ItemService itemService;
	
	
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql",
	})
	@Test
	public void itemEntradaSaveMustPass() {
		ItemEntrada itemEntrada = new ItemEntrada();
		ItemEntrada returnItemEntrada = null;
		Item item = this.itemService.findByCodigo("1234567");
		List<Item> listItem = new ArrayList<>();
		listItem.add(item);
		itemEntrada.setListItem(listItem);
		itemEntrada.setLocalEntrada("Almoxarifado");
		itemEntrada.setQuantidade(3);
		itemEntrada.setValor(30.00);
		
		returnItemEntrada = this.itemEntradaService.entrada(itemEntrada);
		Assert.assertNotNull(returnItemEntrada);
		Assert.assertEquals(3, itemEntrada.getQuantidade());
		
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
						
		
		Assert.assertNotNull(returnItemEntrada);
		Assert.assertEquals(21, returnItemEntrada.getId());
	}
}
