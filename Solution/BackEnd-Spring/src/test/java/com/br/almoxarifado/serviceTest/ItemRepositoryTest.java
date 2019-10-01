package com.br.almoxarifado.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

import com.br.almoxarifado.dto.DtoItem;
import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.error.ExistingItemException;
import com.br.almoxarifado.service.FornecedorService;
import com.br.almoxarifado.service.ItemService;

public class ItemRepositoryTest extends AbstractIntegrationTest {
	@Autowired
	private ItemService service;
	@Autowired
	private FornecedorService fonecedor;

	/**
	 * ====================================== CADASTRAR * ===========================================
	 */

	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void insertMustPass() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(this.fonecedor.convertFornecedor(fornecedores));
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	

	/**
	 * ====================================== LISTAR PELA DESCRICAO ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void findByDescricaoMustPass() {
		Item item = null;
		item = this.service.findByDescricao("Martelo");
		Assert.assertNotNull(item);
		Assert.assertEquals("Martelo", item.getDescricao());
	}

	/**
	 * ====================================== LISTAR PELO ID * ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void findByIdMusPass() {
		Item item = null;
		item = this.service.findByCodigo("1234567");
		Assert.assertNotNull(item.getItemId());
		Assert.assertEquals("Martelo", item.getDescricao());
	}


	/**
	 * ====================================== EDITAR * ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void editMustPass() {
		Item itemFindByCodigo = null;
		Item returnItem = null;
		itemFindByCodigo = this.service.findByCodigo("1234567");
		itemFindByCodigo.setDescricao("Caneta");
		itemFindByCodigo.setLocalArmazenamento("Sitt");
		returnItem = this.service.update(itemFindByCodigo);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals("Caneta", returnItem.getDescricao());
		Assert.assertEquals("Sitt", returnItem.getLocalArmazenamento());
	}

	/**
	 * ====================================== MUST FAIL* ===========================================
	 */

	
	/**
	 * ====================================== INSERT COM DESCRICAO EM BRANCO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = TransactionSystemException.class)
	public void insertMustFailDescricaoNotBlank() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao(" ");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	/**
	 * ====================================== INSERT COM DESCRICAO EM BRANCO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = TransactionSystemException.class)
	public void inserDescricaoBlankMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao(" ");
		item.setLocalArmazenamento("xumodromo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	
	/**
	 * ====================================== INSERT COM LOCAL DE ARMAZENAMENTO EM BRANCO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = TransactionSystemException.class)
	public void insertLocalArmazenamentoBlankMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento(" ");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	
	/**
	 * ====================================== INSERT COM FORNECEDOR NULO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = DataIntegrityViolationException.class)
	public void insertFornecedorNullMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	
	/**
	 * ====================================== INSERT COM QUANTIDADE NULO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test()
	public void insertQuantidadeNullMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor();
		item.setQuantidade(null);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	
	/**
	 * ====================================== INSERT COM VALOR NULO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = TransactionSystemException.class)
	public void insertValorNullMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(null);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	
	/**
	 * ====================================== DESCRICAO MAIOR QUE O CAMPO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = TransactionSystemException.class)
	public void insertDescricaoMaiorQueOCampoMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesouraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	/**
	 * ====================================== LOCAL DE ARMAZENAMENTO MAIOR QUE O CAMPO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = TransactionSystemException.class)
	public void insertLocalArmazenamentoMaiorQueOCampoMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("XumodramoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	/**
	 * ====================================== LOCAL DE ARMAZENAMENTO MAIOR QUE O CAMPO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = ExistingItemException.class)
	public void isertComItemEFornecedorIgualMustFail() {
		DtoItem item = new DtoItem();
		DtoItem returnItem = null;
		Fornecedor fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("ewrwer");
		item.setDescricao("Martelo");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
		item.setQuantidade(43);
		item.setValor(23.00);
		returnItem = this.service.insert(item);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals(returnItem, item);

	}
	/**
	 * ====================================== BUSCA SEM CODIGO CADASTRADO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = IllegalArgumentException.class)
	public void findyidMustFail() {
		this.service.findByCodigo("123123");

	}
	/**
	 * ====================================== BUSCA SEM DESCRICAO INFORMADA ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = IllegalArgumentException.class)
	public void findByDescricaoMusFail() {
		this.service.findByDescricao("sdfsdf");
	}

}
