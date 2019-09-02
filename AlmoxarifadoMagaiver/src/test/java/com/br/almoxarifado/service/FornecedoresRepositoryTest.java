package com.br.almoxarifado.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.enums.TipoEnum;

import org.junit.Assert;


public class FornecedoresRepositoryTest extends AbstractIntegrationTest{
	@Autowired
	private FornecedoresService service;

	@Test
	public void insertFornecedorMustPass() {
		Set<Telefone> listTel = new HashSet<>();
		Telefone tel = new Telefone();
		Telefone telMovel = new Telefone();
		TipoEnum fixo = TipoEnum.FIXO;
		TipoEnum movel = TipoEnum.MOVEL;
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
		tel.setTipoTelefone(fixo.getValor());
		tel.setFornecedor(forn);
		
		//Tel2
		telMovel.setNumero("2323422");
		telMovel.setTipoTelefone(movel.getValor());
		telMovel.setFornecedor(forn);
		
		listTel.add(tel);
		listTel.add(telMovel);

		
		this.service.insertFornecedores(forn,listTel);
	
	}
	@Test
	public void findAllMustPass() {
		Page<Fornecedores> page = null;
		page = this.service.findAll();
		Assert.assertNotNull(page);
	}
	@Test
	public void findNameMustPass() {
		Fornecedores forn = null;
		forn = this.service.findByNomeFornecedor("martelo sa");
		Assert.assertNotNull(forn);
	}
	@Test
	public void editFornecedorAndTelefoneMustPass() {
		Fornecedores fornecedores = null;
		fornecedores = this.service.findByNomeFornecedor("Martelosa");
		fornecedores.setEmail("Henrique@hotmail.com");
		this.service.updateFornecedores(fornecedores);
	}
	@Test(expected = AssertionError.class)
	public void insertEmailMustFailDuplicateEmail() {
		Set<Telefone> listTel = new HashSet<>();
		Telefone tel = new Telefone();
		Telefone telMovel = new Telefone();
		TipoEnum fixo = TipoEnum.FIXO;
		TipoEnum movel = TipoEnum.MOVEL;
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
		tel.setTipoTelefone(fixo.getValor());
		tel.setFornecedor(forn);
		
		//Tel2
		telMovel.setNumero("2323422");
		telMovel.setTipoTelefone(movel.getValor());
		telMovel.setFornecedor(forn);
		
		listTel.add(tel);
		listTel.add(telMovel);

		
		fornecedores = this.service.insertFornecedores(forn,listTel);
		Assert.assertNotNull(fornecedores);
	}
}
