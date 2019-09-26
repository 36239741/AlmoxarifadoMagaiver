package com.br.almoxarifado.service;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedoresService {
	@Autowired
	FornecedorRepository repository;

	// Isere somente se nao existir o mesmo nome cadastrado
	public Fornecedores insertFornecedores(Fornecedores fornecedores) {
		Fornecedores returnFornecedores = null;
		try {
			returnFornecedores = this.repository.save(fornecedores);
		}catch(RollbackException ex) {
			ex.initCause(ex);
		}
		return returnFornecedores;
				
	}


	// Retorna uma Pagina com 10 fornecedores
	public Page<Fornecedores> findAll(int page, int size) {
		Page<Fornecedores> pageFornecedores;
		PageRequest pageable = PageRequest.of(page, size);
		pageFornecedores = this.repository.findAll(pageable);
		return pageFornecedores;
	}



	// Busca pelo nome do fornecedor
	public Fornecedores findByNomeFornecedor(String name) {
		Fornecedores returnFornecedores = this.repository.findByNomeContaining(name);
		Assert.notNull(returnFornecedores, "Fornecedor "+name+" nao encontrado");
		return returnFornecedores;
	}

	// Se o campo fornecedor_status estiver true ele muda para false e vice-versa
	public void desativarOrAtivarFornecedor(String name) {
		Fornecedores fornecedores = null;
		fornecedores = this.findByNomeFornecedor(name);
		if (fornecedores != null) {

			if (fornecedores.getFornecedoresStatus() == true) {
				 this.repository.fornecedorDesative(fornecedores.getFornecedorId());

			} else {
				 this.repository.fornecedorActive(fornecedores.getFornecedorId());
			}
		}
	}
	



}
