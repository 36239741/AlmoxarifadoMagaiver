package com.br.almoxarifado.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.repository.FornecedorRepository;


public class ItemRepositoryTest extends AbstractIntegrationTest {
	@Autowired
	private ItemService service;
	@Autowired
	private FornecedorRepository fonecedor;
	
	@Test
	public void insertMustPass() {
		Item item = new Item();
		item.setDescricao("martelo");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(this.fonecedor.getOne((long)1));
		item.setQuantidade(43);
		item.setValor(23.20);
		this.service.insert(item);
	}
	@Test
	public void findByDescricaoMustPass() {
		Assert.assertNotNull(this.service.findByDescricao("martelo"));
	}
	@Test
	public void findByIdMusPass() {
		Assert.assertNotNull(this.service.findById((long)4));
	}
	@Test
	public void editMustPass() {
		Item itemFindById = null;
		itemFindById = this.service.findById((long)4);
		itemFindById.setDescricao("Caneta");
		itemFindById.setLocalArmazenamento("Sitt");
		Assert.assertNotNull(this.service.edit(itemFindById));	
	}
	
}
