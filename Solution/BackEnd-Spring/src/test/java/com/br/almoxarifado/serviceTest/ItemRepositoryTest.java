package com.br.almoxarifado.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.entity.Item;
import com.br.almoxarifado.error.ExistingItemException;
import com.br.almoxarifado.service.FornecedoresService;
import com.br.almoxarifado.service.ItemService;

public class ItemRepositoryTest extends AbstractIntegrationTest {
	@Autowired
	private ItemService service;
	@Autowired
	private FornecedoresService fonecedor;

	/**
	 * ====================================== CADASTRAR * ===========================================
	 */

	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void insertMustPass() {
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
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
	 * ====================================== CADASTRAR 2 * ===========================================
	 */

	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void insert2MustPass() {
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
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
		item = this.service.findById(100);
		Assert.assertNotNull(item.getId());
		Assert.assertEquals("Martelo", item.getDescricao());
	}


	/**
	 * ====================================== EDITAR * ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void editMustPass() {
		Item itemFindById = null;
		Item returnItem = null;
		itemFindById = this.service.findById(100);
		itemFindById.setDescricao("Caneta");
		itemFindById.setLocalArmazenamento("Sitt");
		returnItem = this.service.insert(itemFindById);
		Assert.assertNotNull(returnItem);
		Assert.assertEquals("Caneta", returnItem.getDescricao());
		Assert.assertEquals("Sitt", returnItem.getLocalArmazenamento());
	}

	/**
	 * ====================================== LISTAR PELO LOCAL DE ARMAZENAMENTO * ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test
	public void findByLocalArmazenamentoMusPass() {
		Item item = null;
		item = this.service.findByLocalDeArmazenamento("Loja1");
		Assert.assertNotNull(item.getId());
		Assert.assertEquals("Martelo", item.getDescricao());
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
	@Test(expected = TransactionSystemException.class)
	public void insertQuantidadeNullMustFail() {
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
		fornecedores = this.fonecedor.findByNomeFornecedor("www");
		item.setDescricao("Tesoura");
		item.setLocalArmazenamento("Xumodramo");
		item.setFornecedor(fornecedores);
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
		Item item = new Item();
		Item returnItem = null;
		Fornecedores fornecedores = null;
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
	 * ====================================== BUSCA SEM ID CADASTRADO ===========================================
	 */
	
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = IllegalArgumentException.class)
	public void findyidMustFail() {
		this.service.findById(90);

	}
	/**
	 * ====================================== BUSCA SEM DESCRICAO INFORMADA ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = IllegalArgumentException.class)
	public void findByDescricaoMusFail() {
		this.service.findByDescricao("sdfsdf");
	}
	/**
	 * ====================================== BUSCA SEM LOCAL DE ARMAZENAMTO INFORMADO ===========================================
	 */
	@Sql({ "/dataset/truncateItem.sql", "/dataset/item.sql" })
	@Test(expected = IllegalArgumentException.class)
	public void findByLocalArmazenamentoMustFail() {
		this.service.findByLocalDeArmazenamento("asdasd");
	}
}
