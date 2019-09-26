package com.br.almoxarifado.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.enums.TipoTelefone;
import com.br.almoxarifado.service.FornecedoresService;


public class FornecedoresRepositoryTest extends AbstractIntegrationTest{
	@Autowired
	private FornecedoresService service;

	@Test
	public void insertFornecedorMustPass() {
		List<Telefone> listTel = new ArrayList<Telefone>();
		Telefone tel = new Telefone();
		Telefone telMovel = new Telefone();
		//Fornecedor
		Fornecedores forn = new Fornecedores();
		forn.setNome("ASSA");
		forn.setEmail("martlo@hotmail.com");
		forn.setBairro("curitbano");
		forn.setCep("123123");
		forn.setCidade("foz do iguau");
		forn.setNumero(2);
		forn.setEstado("PR");
		forn.setPais("BR");
		forn.setLongadouro("igarapava");
		
		//Tel1
		tel.setNumero("234234");
		tel.setTipoTelefone(TipoTelefone.FIXO);

		
		//Tel2
		telMovel.setNumero("2323422");
		telMovel.setTipoTelefone(TipoTelefone.MOVEL);

		
		listTel.add(tel);
		listTel.add(telMovel);
		forn.setTelefone(listTel);
		
		
		this.service.insertFornecedores(forn);
	
	}
	@Test
	public void findAllMustPass() {
		Page<Fornecedores> page = null;
		page = this.service.findAll(1,1);
		Assert.assertNotNull(page);
	}
	@Test
	public void fornecedorAtive() {
		this.service.desativarOrAtivarFornecedor("MARTELOSA");
	}
	@Test
	public void findNameMustPass() {
		Fornecedores forn = null;
		forn = this.service.findByNomeFornecedor("qweqe");
		Assert.assertNotNull(forn);
		Assert.assertEquals("qweqe", forn.getNome());
	}
	@Test
	public void editFornecedorAndTelefoneMustPass() {
		Fornecedores fornecedores = null;
		Fornecedores returnFornecedores = null;
		fornecedores = this.service.findByNomeFornecedor("Martelosa");
		fornecedores.setEmail("Henrique@hotmail.com");
		returnFornecedores = this.service.insertFornecedores(fornecedores);
		Assert.assertEquals(fornecedores.getEmail() ,returnFornecedores.getEmail());
	}
	/*@Test(expected = AssertionError.class)
	public void insertEmailMustFailDuplicateEmail() {
		Set<Telefone> listTel = new HashSet<>();
		Telefone tel = new Telefone();
		Telefone telMovel = new Telefone();
		Fornecedores fornecedores = null;
		//Fornecedor
		Fornecedores forn = new Fornecedores();
		forn.setNome("Martelo SA");
		forn.setEmail("martlo@hotmail.com");
		forn.setBairro("curitbano");
		forn.setCep("123123");
		forn.setCidade("foz do iguau");
		forn.setNumero(2);
		forn.setEstado("PR");
		forn.setPais("BR");
		forn.setLongadouro("igarapava");
		
		//Tel1
		tel.setNumero("234234");
		tel.setTipoTelefone(TipoTelefone.FIXO);
		tel.setFornecedor(forn);
		
		//Tel2
		telMovel.setNumero("2323422");
		telMovel.setTipoTelefone(TipoTelefone.MOVEL);
		telMovel.setFornecedor(forn);
		
		listTel.add(tel);
		listTel.add(telMovel);

		
		this.service.insertFornecedores(forn,listTel);
		Assert.assertNotNull(fornecedores);
	}*/
}
