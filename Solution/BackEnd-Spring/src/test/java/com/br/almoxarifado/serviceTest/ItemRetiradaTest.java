package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.ItemRetirada;
import com.br.almoxarifado.service.ItemRetiradaService;
import com.br.almoxarifado.service.ItemService;

public class ItemRetiradaTest extends AbstractIntegrationTest{
	
	@Autowired
	private ItemRetiradaService itemRetiradaService;
		
	@Autowired
	private ItemService itemService; 
	
	
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql",
			"/dataset/truncateItemRetirada.sql",
			"/dataset/itemRetirada.sql",
	})
	@Test
	public void itemRetiradaSaveMustPass() {
		ItemRetirada itemRetirada = new ItemRetirada();
		ItemRetirada returnItemRetirada = null;
		Item item =this.itemService.findByCodigo("1234567");
		List<Item> listItem = new ArrayList<>();
		listItem.add(item);
		itemRetirada.setListItem(listItem);
		itemRetirada.setLocalRetirada("Almoxarifado");
		itemRetirada.setQuantidade(3);
		itemRetirada.setValor(45.00);
		
		returnItemRetirada = this.itemRetiradaService.retirada(itemRetirada);
		Assert.assertNotNull(returnItemRetirada);
		Assert.assertEquals(3, itemRetirada.getQuantidade());
		
	}
	
	@Sql(scripts = {
			"/dataset/truncateItemRetirada.sql",
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
			"/dataset/truncateItemRetirada.sql",
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
