package com.br.almoxarifado.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.enums.TipoEnum;

public class FornecedoresRepositoryTest extends AbstractIntegrationTest{
	@Autowired
	private FornecedoresService service;
	@Autowired
	private TelefoneService serviceTel;
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

		
		this.service.insertOrUpdate(forn);
		this.serviceTel.insertTelefone(listTel);
	}
}
