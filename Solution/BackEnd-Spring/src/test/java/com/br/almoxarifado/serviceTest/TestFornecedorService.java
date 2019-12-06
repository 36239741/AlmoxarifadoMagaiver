package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

import com.br.almoxarifado.dto.DtoFornecedor;
import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.enums.TipoTelefone;
import com.br.almoxarifado.service.FornecedorService;





						
/*ATRIBUTOS	*/
						
						
/*CONSTRUCAO*/
							
							
/*TESTE*/

public class TestFornecedorService extends AbstractIntegrationTest {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	
									/* TESTE DE PERSISTIR UM FORNECEDOR */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	@Test
	public void testFornecedorSaveMustPass() {
		/*ATRIBUTOS	*/
		Fornecedor fornecedor = new Fornecedor();
		DtoFornecedor returnFornecedor= null;
		Telefone tel1 = new Telefone();
		List<Telefone> list = new ArrayList<Telefone>();
		
		/*CONSTRUCAO*/
		tel1.setNumero("323213123");
		tel1.setTipoTelefone(TipoTelefone.MOVEL);
		list.add(tel1);
		fornecedor.setNome("Henrique");
		fornecedor.setBairro("Curitibano");
		fornecedor.setCep("85869696");
		fornecedor.setCidade("Foz do Iguacu");
		fornecedor.setEmail("henrique_nitatori@hotmail.com");
		fornecedor.setEstado("SP");
		fornecedor.setLongadouro("Igarapava");
		fornecedor.setNumero(875);
		fornecedor.setPais("BR");
		fornecedor.setTelefone(list);
		returnFornecedor = this.fornecedorService.insertFornecedores(this.fornecedorService.convertFornecedor(fornecedor));
		
		/*TESTE*/
		Assert.assertNotNull(returnFornecedor);
		Assert.assertEquals("Henrique",returnFornecedor.getNome());
		Assert.assertEquals("Curitibano",returnFornecedor.getBairro());
		
		
	}
	
							/* TESTE DE BUSCAR TODOS OS FORNECEDORES RETORNADO UMA PAGEABLE */
	@Test
	public void testFornecedorFindAllMustPass() {
		/*ATRIBUTOS	*/
		Page<Fornecedor> returnFornecedores = null;
		
		/*CONSTRUCAO*/
		returnFornecedores = this.fornecedorService.findAll(0, 1);						
									
		/*TESTE*/
		Assert.assertNotNull(returnFornecedores);
	}
	
										/* TESTE DE BUSCAR UM FORNECEDOR PELO NOME */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	@Test
	public void testFornecedorFindBynameMustPass() {
		/*ATRIBUTOS	*/
		Fornecedor returnFornecedor = null;
		
		/*CONSTRUCAO*/
		returnFornecedor = this.fornecedorService.findByNomeFornecedor("Teste");
									
		/*TESTE*/
		Assert.assertNotNull(returnFornecedor);
		Assert.assertEquals("teste", returnFornecedor.getNome());
	}
	
								/* TESTE DE DESATIVAR FORNECEDOR PELO NOME */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	@Test
	public void testFornecedorAtivaDesativaMustPass() {
		/*ATRIBUTOS	*/
		Fornecedor returnFornecedor = null;
		
		/*CONSTRUCAO*/
		this.fornecedorService.desativarOrAtivarFornecedor("Teste");
		returnFornecedor = this.fornecedorService.findByNomeFornecedor("Teste");
									
		/*TESTE*/
		Assert.assertNotNull(returnFornecedor);
		Assert.assertEquals(false, returnFornecedor.getFornecedorStatus());
	}
	
									/* TESTE DE ATIVAR FORNECEDOR PELO NOME */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	
	@Test()
	public void testFornecedorAtivaDesativaMustPass2() {
		/*ATRIBUTOS	*/
		Fornecedor fornecedor = new Fornecedor();
		Telefone tel1 = new Telefone();
		Fornecedor findFornecedor = null;
		List<Telefone> list = new ArrayList<Telefone>();
		
		/*CONSTRUCAO*/
		tel1.setNumero("323213123");
		tel1.setTipoTelefone(TipoTelefone.MOVEL);
		list.add(tel1);
		fornecedor.setNome("Henrique");
		fornecedor.setBairro("Curitibano");
		fornecedor.setCep("85869696");
		fornecedor.setCidade("Foz do Iguacu");
		fornecedor.setEmail("henrique_nitatori@hotmail.com");
		fornecedor.setEstado("SP");
		fornecedor.setLongadouro("Igarapava");
		fornecedor.setNumero(875);
		fornecedor.setPais("BR");
		fornecedor.setFornecedorStatus(false);
		fornecedor.setTelefone(list);
		this.fornecedorService.insertFornecedores(this.fornecedorService.convertFornecedor(fornecedor));
		this.fornecedorService.desativarOrAtivarFornecedor("Henrique");
		findFornecedor = this.fornecedorService.findByNomeFornecedor("Henrique");
		
		/* TESTE */
		Assert.assertNotNull(findFornecedor);
		Assert.assertEquals(true, findFornecedor.getFornecedorStatus());
	

	}
	
	
										/* MUSTFAIL */
	
							/* CADASTRANDO FORNECEDOR COM CAMPO STRING VAZIO */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql"
	})
	@Test(expected = TransactionSystemException.class)
	public void testFornecedorSaveMustFail() {
		/*ATRIBUTOS	*/
		Fornecedor fornecedor = new Fornecedor();
		DtoFornecedor returnFornecedor= null;
		Telefone tel1 = new Telefone();
		List<Telefone> list = new ArrayList<Telefone>();
		
		/*CONSTRUCAO*/
		tel1.setNumero("323213123");
		tel1.setTipoTelefone(TipoTelefone.MOVEL);
		list.add(tel1);
		fornecedor.setNome("");
		fornecedor.setBairro("Curitibano");
		fornecedor.setCep("85869696");
		fornecedor.setCidade("Foz do Iguacu");
		fornecedor.setEmail("henrique_nitatori@hotmail.com");
		fornecedor.setEstado("SP");
		fornecedor.setLongadouro("Igarapava");
		fornecedor.setNumero(875);
		fornecedor.setPais("BR");
		fornecedor.setTelefone(list);
		returnFornecedor = this.fornecedorService.insertFornecedores(this.fornecedorService.convertFornecedor(fornecedor));
		
		/*TESTE*/
		Assert.assertNotNull(returnFornecedor);
		Assert.assertEquals("Henrique",returnFornecedor.getNome());
		Assert.assertEquals("Curitibano",returnFornecedor.getBairro());
		
		
	}

									/* CADASTRANDO FORNCEDOR COM NOME DUPLICADO */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	@Test(expected = DataIntegrityViolationException.class)
	public void testFornecedorSaveMustFail2() {
		/*ATRIBUTOS	*/
		Fornecedor fornecedor = new Fornecedor();
		Telefone tel1 = new Telefone();
		List<Telefone> list = new ArrayList<Telefone>();
		
		/*CONSTRUCAO*/
		tel1.setNumero("323213123");
		tel1.setTipoTelefone(TipoTelefone.MOVEL);
		list.add(tel1);
		fornecedor.setNome("teste");
		fornecedor.setBairro("Curitibano");
		fornecedor.setCep("85869696");
		fornecedor.setCidade("Foz do Iguacu");
		fornecedor.setEmail("henrique_nitatori@hotmail.com");
		fornecedor.setEstado("SP");
		fornecedor.setLongadouro("Igarapava");
		fornecedor.setNumero(875);
		fornecedor.setPais("BR");
		fornecedor.setTelefone(list);
		this.fornecedorService.insertFornecedores(this.fornecedorService.convertFornecedor(fornecedor));
		
		/*TESTE*/
	}

								/* BUSCANDO FORNECEDOR POR NOME INEXISTENTE */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	@Test(expected = IllegalArgumentException.class)
	public void testFornecedorFindBynameMustFail() {
		/*ATRIBUTOS	*/
		
		
		/*CONSTRUCAO*/
		this.fornecedorService.findByNomeFornecedor("Henrique");
									
		/*TESTE*/

	}

									/* DESATIVANDO FORNECEDOR COM NOME INEXISTENTE */
	@Sql(scripts = {
			"/dataset/truncateFornecedor.sql",
			"/dataset/fornecedor.sql"
	})
	@Test(expected = IllegalArgumentException.class)
	public void testFornecedorAtivaDesativaMustFail() {
		/*ATRIBUTOS	*/
		
		
		/*CONSTRUCAO*/
		this.fornecedorService.desativarOrAtivarFornecedor("Henrique");
		
									
		/*TESTE*/

	}
}
