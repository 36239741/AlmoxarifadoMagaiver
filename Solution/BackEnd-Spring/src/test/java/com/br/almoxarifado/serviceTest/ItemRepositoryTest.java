package com.br.almoxarifado.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.service.FornecedoresService;
import com.br.almoxarifado.service.ItemService;


public class ItemRepositoryTest extends AbstractIntegrationTest {
	@Autowired
	private ItemService service;
	@Autowired
	private FornecedoresService fonecedor;
	
	@Test
	public void insertMustPass() {
		Item item = new Item();
		Fornecedores fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("MarteloSA");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.20);
		this.service.insert(item);

	}
	@Test
	public void findByDescricaoMustPass() {
		Assert.assertNotNull(this.service.findByDescricao("Tesoura"));
	}
	@Test
	public void findByIdMusPass() {
		Assert.assertNotNull(this.service.findById(31L));
	}
	@Test
	public void editMustPass() {
		Item itemFindById = null;
		itemFindById = this.service.findById(31L);
		itemFindById.setDescricao("Caneta");
		itemFindById.setLocalArmazenamento("Sitt");
		Assert.assertNotNull(this.service.edit(itemFindById));	
	}
	
}
