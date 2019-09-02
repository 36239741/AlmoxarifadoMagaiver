package com.br.almoxarifado.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.repository.TelefoneRepository;

@Service
@Transactional
public class TelefoneService   {
	@Autowired
	private TelefoneRepository repositoy;
	
	public Set<Telefone> insertTelefone(Set<Telefone> telefone) {
			for(Telefone p : telefone) {
				this.repositoy.save(p);
			}
		return telefone;
	}
	public Telefone findTelefoneByIdFornecedor(Long fornecedorId, Integer tipoTelefone) {
		Telefone findTelefone = null;
			findTelefone = this.repositoy.findTelefoneByIdFornecedor(fornecedorId, tipoTelefone);
		return findTelefone;
	}
	
}
