package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.enums.TipoTelefone;
import com.br.almoxarifado.error.ExistingItemException;
import com.br.almoxarifado.repository.FornecedorRepository;
import com.br.almoxarifado.repository.ItemRepository;
import com.br.almoxarifado.service.ItemService;


public class ItemServiceTest extends AbstractIntegrationTest {
	@Autowired
	private ItemService service;
	@Autowired 
	private FornecedorRepository fornecedorRepository;
	@Autowired
	private ItemRepository ItemRepository;
												/* MUST PASS */
											/* CADASTRAR ITEM */
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql"
	})
	@Test
	public void saveItemMustPassSalvandoUmItem() {
		
	/* ATRIBUTOS */
	Fornecedor fornecedor = null;
	Item item = new Item();
	Item returnItem = null;
	
	/*CONSTRUCAO*/
	fornecedor = this.fornecedorRepository.findByNomeIgnoreCase("teste");
	item.setDescricao("Switch 2960X");
	item.setFornecedor(fornecedor);
	item.setLocalArmazenamento("Shumodramo");
	item.setQuantidade(24);
	item.setValor(7000.00);
	returnItem = this.service.saveItem(item);
	
	/*TESTE*/
	Assert.assertNotNull(returnItem);
	Assert.assertEquals("Switch 2960X", returnItem.getDescricao());
	Assert.assertEquals("Shumodramo", returnItem.getLocalArmazenamento());
	
	}
	
												/* CADASTRAR ITEM COM A MESMA DESCRICAO
												 * E O FORNECEDOR DIFERENTE */
	
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql"
			
	})
	@Test
	public void saveItemMustPassSalvandoUmItemComAMesmaDescricaoEoFornecedorDiferente() {
	
	/* ATRIBUTOS */
	Item returnItem = null;
	Item returnItem2 = null;
	Item item = new Item();
	Item item2 = new Item();
	Fornecedor findFornecedor = null;
	Fornecedor findFornecedor2 = null;
	
	/*CONSTRUCAO*/
	findFornecedor = this.fornecedorRepository.findByNomeIgnoreCase("teste");
	findFornecedor2 = this.fornecedorRepository.findByNomeIgnoreCase("teste2");
	item.setDescricao("Switch 3750");
	item.setFornecedor(findFornecedor);
	item.setLocalArmazenamento("Shumodramo");
	item.setQuantidade(24);
	item.setValor(7000.00);
	
	item2.setDescricao("Switch 3750");
	item2.setFornecedor(findFornecedor2);
	item2.setLocalArmazenamento("Shumodramo"); 
	item2.setQuantidade(24);
	item2.setValor(12000.00);
	returnItem2 = this.service.saveItem(item2);
	returnItem = this.service.saveItem(item);
	
	/*TESTE*/
	Assert.assertNotNull(returnItem);
	Assert.assertEquals("Switch 3750", returnItem.getDescricao());
	Assert.assertEquals("Shumodramo", returnItem.getLocalArmazenamento());
	
	Assert.assertNotNull(returnItem);
	Assert.assertEquals("Switch 3750", returnItem2.getDescricao());
	Assert.assertEquals("Shumodramo", returnItem2.getLocalArmazenamento());

}
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void filterItemItemMustPassFiltrarItemPeloCodigo() {
	Page<Item> item = this.service.itemFilter("1234567", "", "", PageRequest.of(0, 10));
	
	Assert.assertNotNull(item);
	
	}
	
											/* CADASTRAR ITEM COM MESMO FORNECEDOR E 
											 * A  DESCRICAO DO ITEM DIFERENTE */

	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void saveItemMustPassCadastrarItemComMesmoFornecedorEdescricaoDiferente() {
	
	/* ATRIBUTOS */
	Item returnItem = null;
	Item returnItem2 = null;
	Item item = new Item();
	Item item2 = new Item();
	Fornecedor findFornecedor = null;
	
	/*CONSTRUCAO*/
	findFornecedor = this.fornecedorRepository.findByNomeIgnoreCase("teste");
	item.setDescricao("Switch 2960X");
	item.setFornecedor(findFornecedor);
	item.setLocalArmazenamento("Shumodramo");
	item.setQuantidade(24);
	item.setValor(7000.00);
	
	item2.setDescricao("Switch 3750");
	item2.setFornecedor(findFornecedor);
	item2.setLocalArmazenamento("Shumodramo"); 
	item2.setQuantidade(24);
	item2.setValor(12000.00);
	returnItem2 = this.service.saveItem(item2);
	returnItem = this.service.saveItem(item);
	
	/*TESTE*/
	Assert.assertNotNull(returnItem);
	Assert.assertEquals("Switch 2960X", returnItem.getDescricao());
	Assert.assertEquals("Shumodramo", returnItem.getLocalArmazenamento());
	
	Assert.assertNotNull(returnItem);
	Assert.assertEquals("Switch 3750", returnItem2.getDescricao());
	Assert.assertEquals("Shumodramo", returnItem2.getLocalArmazenamento());
	
	}
	
												/* UPDATE*/
	
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void updateItemMustPassFazUpdateDeUmItem() {
	/*ATRIBUTOS	*/
	Item findItem = null;
	Item item = null;
	
	/*CONSTRUCAO*/
	findItem = this.ItemRepository.findByCodigo("1234567");
	findItem.setDescricao("Switch 2960X");
	findItem.setLocalArmazenamento("Data Center");
	findItem.setItemStatus(false);
	item = this.service.saveItem(findItem);
	/*TESTE*/
	
	Assert.assertNotNull(item);
	Assert.assertEquals("Switch 2960X", item.getDescricao());
	Assert.assertEquals("Data Center", item.getLocalArmazenamento());
	}

														/* FIND ALL */
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void findAllMustPassBuscaTodosItens() {
	/*ATRIBUTOS	*/
	Page<Item> page = null;
	
	/*CONSTRUCAO*/
	page = this.service.findAllItem(0, 1);
	
			
	/*TESTE*/
	Assert.assertNotNull(page);
	Assert.assertEquals(1,page.getTotalElements());
	}
	
												/* FIND BY CODIGO */
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void findByCodigoMustPassBuscaUmItemPeloCodigo() {
	/*ATRIBUTOS	*/
	Item findItem = null;
	
	/*CONSTRUCAO*/
	findItem = this.service.findByCodigo("1234567");
	
	/*TESTE*/
	Assert.assertNotNull(findItem);
	Assert.assertEquals("1234567", findItem.getCodigo());
	Assert.assertEquals("Martelo",findItem.getDescricao());
	}
	
	
	
	
											/* MUST FAIL */
						/* SAVE ITEM FAIL, COM O MESMA DESCRICAO E O MESMO 
										 * FORNECEDOR */
	@Sql(value = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql"
	})
	@Test(expected = ExistingItemException.class)
	public void testSaveItemMustFail() {
		/* ATRIBUTOS */
		List<Telefone> listTelefone = new ArrayList<Telefone>();
		List<Item> listItem = new ArrayList<Item>();
		Item returnItem = null;
		Telefone telefone1 = new Telefone(1,TipoTelefone.FIXO,"36239541");
		Telefone telefone2 = new Telefone(2,TipoTelefone.MOVEL,"955654455");
		Fornecedor fornecedor = new Fornecedor(1,"teste", "henrique_nitatori@hotmail.com", listTelefone, listItem, 
				"5694-6658", "Igarapava", 875, "Curitabano", "Foz do Iguacu", "PR", "BR",true);
		Item item = new Item();
		
		/*CONSTRUCAO*/
		listTelefone.add(telefone1);
		listTelefone.add(telefone2);
		item.setDescricao("Martelo");
		item.setFornecedor(fornecedor);
		item.setLocalArmazenamento("Shumodramo");
		item.setQuantidade(24);
		item.setValor(7000.00);
		
		/*TESTE*/
		returnItem = this.service.saveItem(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);
	}
	
												/* FIND BY CODIGO COM CODIGO INVALIDO */
		@Sql(scripts = {
		"/dataset/truncateItem.sql",
		"/dataset/item.sql"
		
		})
		@Test(expected = IllegalArgumentException.class)
		public void testFindByCodigoMustFail() {
		/*ATRIBUTOS	*/
		Item findItem = null;
		
		/*CONSTRUCAO*/
		findItem = this.service.findByCodigo("21dDa2");
		
		/*TESTE*/
		Assert.assertNotNull(findItem);
		}

									/* INSERINDO ITEM COM  UM CAMPO STRING VAZIO */
		@Sql(scripts = {
				"/dataset/truncateItem.sql",
				"/dataset/item.sql"
		})
		@Test(expected = TransactionSystemException.class)
		public void testSaveItemMustFail2() {
			
		/* ATRIBUTOS */
		Fornecedor fornecedor = null;
		Item item = new Item();
		Item returnItem = null;
		
		/*CONSTRUCAO*/
		fornecedor = this.fornecedorRepository.findByNomeIgnoreCase("teste");
		item.setDescricao("");
		item.setFornecedor(fornecedor);
		item.setLocalArmazenamento("Shumodramo");
		item.setQuantidade(24);
		item.setValor(7000.00);
		returnItem = this.service.saveItem(item);
		
		/*TESTE*/
		Assert.assertNotNull(returnItem);
		Assert.assertEquals("Switch 2960X", returnItem.getDescricao());
		Assert.assertEquals("Shumodramo", returnItem.getLocalArmazenamento());
		
		}
		
											/* INSERINDO ITEM COM CAMPO INT COM VALOR NULO */
		@Sql(scripts = {
		"/dataset/truncateItem.sql",
		"/dataset/item.sql"
		})
		@Test(expected = TransactionSystemException.class)
		public void testSaveItemMustFail3() {
		
		/* ATRIBUTOS */
		Fornecedor fornecedor = null;
		Item item = new Item();
		Item returnItem = null;
		
		/*CONSTRUCAO*/
		fornecedor = this.fornecedorRepository.findByNomeIgnoreCase("teste");
		item.setDescricao("Switch 2960");
		item.setFornecedor(fornecedor);
		item.setLocalArmazenamento("Shumodramo");
		item.setQuantidade(null);
		item.setValor(7000.00);
		returnItem = this.service.saveItem(item);
		
		/*TESTE*/
		Assert.assertNotNull(returnItem);
		Assert.assertEquals("Switch 2960X", returnItem.getDescricao());
		Assert.assertEquals("Shumodramo", returnItem.getLocalArmazenamento());
		
		}
	

}
