package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

import com.br.almoxarifado.dto.DtoItemReponse;
import com.br.almoxarifado.dto.DtoItemRequest;
import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.enums.TipoTelefone;
import com.br.almoxarifado.error.ExistingItemException;
import com.br.almoxarifado.service.FornecedorService;
import com.br.almoxarifado.service.ItemService;

/*ATRIBUTOS	*/


/*CONSTRUCAO*/
	
	
/*TESTE*/

public class TestItemService extends AbstractIntegrationTest {
	@Autowired
	private ItemService service;
	@Autowired 
	private FornecedorService serviceFornecedor;
												/* MUST PASS */
											/* CADASTRAR ITEM */
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql"
	})
	@Test
	public void testSaveItemMustPass() {
		
	/* ATRIBUTOS */
	Fornecedor fornecedor = null;
	DtoItemRequest dtoItem = new DtoItemRequest();
	DtoItemRequest returnDto = null;
	
	/*CONSTRUCAO*/
	fornecedor = this.serviceFornecedor.findByNomeFornecedor("teste");
	dtoItem.setDescricao("Switch 2960X");
	dtoItem.setFornecedor(this.serviceFornecedor.convertFornecedorItem(fornecedor));
	dtoItem.setLocalArmazenamento("Shumodramo");
	dtoItem.setQuantidade(24);
	dtoItem.setValor(7000.00);
	returnDto = this.service.insert(dtoItem);
	
	/*TESTE*/
	Assert.assertNotNull(returnDto);
	Assert.assertEquals("Switch 2960X", returnDto.getDescricao());
	Assert.assertEquals("Shumodramo", returnDto.getLocalArmazenamento());
	
	}
	
												/* CADASTRAR ITEM COM A MESMA DESCRICAO
												 * E O FORNECEDOR DIFERENTE */
	
	@Sql(scripts = {
			"/dataset/truncateItem.sql",
			"/dataset/item.sql"
			
	})
	@Test
	public void testSaveItem2MustPass() {
	
	/* ATRIBUTOS */
	DtoItemRequest returnDto = null;
	DtoItemRequest returnDto2 = null;
	DtoItemRequest dtoItem = new DtoItemRequest();
	DtoItemRequest dtoItem2 = new DtoItemRequest();
	Fornecedor findFornecedor = null;
	Fornecedor findFornecedor2 = null;
	
	/*CONSTRUCAO*/
	findFornecedor = this.serviceFornecedor.findByNomeFornecedor("teste");
	findFornecedor2 = this.serviceFornecedor.findByNomeFornecedor("teste2");
	dtoItem.setDescricao("Switch 3750");
	dtoItem.setFornecedor(this.serviceFornecedor.convertFornecedorItem(findFornecedor));
	dtoItem.setLocalArmazenamento("Shumodramo");
	dtoItem.setQuantidade(24);
	dtoItem.setValor(7000.00);
	
	dtoItem2.setDescricao("Switch 3750");
	dtoItem2.setFornecedor(this.serviceFornecedor.convertFornecedorItem(findFornecedor2));
	dtoItem2.setLocalArmazenamento("Shumodramo"); 
	dtoItem2.setQuantidade(24);
	dtoItem2.setValor(12000.00);
	returnDto2 = this.service.insert(dtoItem2);
	returnDto = this.service.insert(dtoItem);
	
	/*TESTE*/
	Assert.assertNotNull(returnDto);
	Assert.assertEquals("Switch 3750", returnDto.getDescricao());
	Assert.assertEquals("Shumodramo", returnDto.getLocalArmazenamento());
	
	Assert.assertNotNull(returnDto);
	Assert.assertEquals("Switch 3750", returnDto2.getDescricao());
	Assert.assertEquals("Shumodramo", returnDto2.getLocalArmazenamento());

}
											/* CADASTRAR ITEM COM MESMO FORNECEDOR E 
											 * A  DESCRICAO DO ITEM DIFERENTE */

	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void testSaveItem3MustPass() {
	
	/* ATRIBUTOS */
	DtoItemRequest returnDto = null;
	DtoItemRequest returnDto2 = null;
	DtoItemRequest dtoItem = new DtoItemRequest();
	DtoItemRequest dtoItem2 = new DtoItemRequest();
	Fornecedor findFornecedor = null;
	
	/*CONSTRUCAO*/
	findFornecedor = this.serviceFornecedor.findByNomeFornecedor("teste");
	dtoItem.setDescricao("Switch 2960X");
	dtoItem.setFornecedor(this.serviceFornecedor.convertFornecedorItem(findFornecedor));
	dtoItem.setLocalArmazenamento("Shumodramo");
	dtoItem.setQuantidade(24);
	dtoItem.setValor(7000.00);
	
	dtoItem2.setDescricao("Switch 3750");
	dtoItem2.setFornecedor(this.serviceFornecedor.convertFornecedorItem(findFornecedor));
	dtoItem2.setLocalArmazenamento("Shumodramo"); 
	dtoItem2.setQuantidade(24);
	dtoItem2.setValor(12000.00);
	returnDto2 = this.service.insert(dtoItem2);
	returnDto = this.service.insert(dtoItem);
	
	/*TESTE*/
	Assert.assertNotNull(returnDto);
	Assert.assertEquals("Switch 2960X", returnDto.getDescricao());
	Assert.assertEquals("Shumodramo", returnDto.getLocalArmazenamento());
	
	Assert.assertNotNull(returnDto);
	Assert.assertEquals("Switch 3750", returnDto2.getDescricao());
	Assert.assertEquals("Shumodramo", returnDto2.getLocalArmazenamento());
	
	}
	
												/* UPDATE*/
	
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void testUpdateItemMustPass() {
	/*ATRIBUTOS	*/
	Item findItem = null;
	DtoItemRequest Dtoitem = null;
	
	/*CONSTRUCAO*/
	findItem = this.service.findByCodigo("1234567");
	findItem.setDescricao("Switch 2960X");
	findItem.setLocalArmazenamento("Data Center");
	findItem.setItemStatus(false);
	Dtoitem = this.service.update(findItem);
	/*TESTE*/
	
	Assert.assertNotNull(Dtoitem);
	Assert.assertEquals("Switch 2960X", Dtoitem.getDescricao());
	Assert.assertEquals("Data Center", Dtoitem.getLocalArmazenamento());
	}

														/* FIND ALL */
	@Sql(scripts = {
	"/dataset/truncateItem.sql",
	"/dataset/item.sql"
	
	})
	@Test
	public void testfindAllMustPass() {
	/*ATRIBUTOS	*/
	Page<DtoItemReponse> page = null;
	
	/*CONSTRUCAO*/
	page = this.service.findAllIten(0, 1);
	
			
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
	public void testFindByCodigoMustPass() {
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
		DtoItemRequest returnDto = null;
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
		returnDto = this.service.insert(this.service.convertItem(item));
		Assert.assertNotNull(returnDto);
		Assert.assertEquals(item, returnDto);
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
		DtoItemRequest dtoItem = new DtoItemRequest();
		DtoItemRequest returnDto = null;
		
		/*CONSTRUCAO*/
		fornecedor = this.serviceFornecedor.findByNomeFornecedor("teste");
		dtoItem.setDescricao("");
		dtoItem.setFornecedor(this.serviceFornecedor.convertFornecedorItem(fornecedor));
		dtoItem.setLocalArmazenamento("Shumodramo");
		dtoItem.setQuantidade(24);
		dtoItem.setValor(7000.00);
		returnDto = this.service.insert(dtoItem);
		
		/*TESTE*/
		Assert.assertNotNull(returnDto);
		Assert.assertEquals("Switch 2960X", returnDto.getDescricao());
		Assert.assertEquals("Shumodramo", returnDto.getLocalArmazenamento());
		
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
		DtoItemRequest dtoItem = new DtoItemRequest();
		DtoItemRequest returnDto = null;
		
		/*CONSTRUCAO*/
		fornecedor = this.serviceFornecedor.findByNomeFornecedor("teste");
		dtoItem.setDescricao("Switch 2960");
		dtoItem.setFornecedor(this.serviceFornecedor.convertFornecedorItem(fornecedor));
		dtoItem.setLocalArmazenamento("Shumodramo");
		dtoItem.setQuantidade(null);
		dtoItem.setValor(7000.00);
		returnDto = this.service.insert(dtoItem);
		
		/*TESTE*/
		Assert.assertNotNull(returnDto);
		Assert.assertEquals("Switch 2960X", returnDto.getDescricao());
		Assert.assertEquals("Shumodramo", returnDto.getLocalArmazenamento());
		
		}
	

}
