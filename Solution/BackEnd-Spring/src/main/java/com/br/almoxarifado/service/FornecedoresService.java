package com.br.almoxarifado.service;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.error.ResourceNotFoundException;
import com.br.almoxarifado.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedoresService {
	@Autowired
	FornecedorRepository repository;

	// Isere somente se nao existir o mesmo nome cadastrado
	public void insertFornecedores(Fornecedores fornecedores) {
		try {
		String fornecedorNameFilter = null;
			fornecedorNameFilter = this.filterNameFornecedor(fornecedores.getNome());
			fornecedores.setNome(fornecedorNameFilter);
			this.repository.save(fornecedores);
		}catch(RollbackException ex) {
			ex.initCause(ex);
		}
		
	}

	// Edita o fornecedor somente se achar ele no banco
	public Fornecedores updateFornecedores(Fornecedores fornecedores) {
		String filterFornecedorName = null;
		Fornecedores fornecedoresPersist = null;
		Fornecedores findFornecedores;
		filterFornecedorName = this.filterNameFornecedor(fornecedores.getNome());
		findFornecedores = this.findByNomeFornecedor(filterFornecedorName);
		if (findFornecedores != null) {
			fornecedoresPersist = this.repository.save(fornecedores);
		}

		return fornecedoresPersist;
	}

	// Retorna uma Pagina com 10 fornecedores
	public Page<Fornecedores> findAll() {
		Page<Fornecedores> page;
		PageRequest pageable = PageRequest.of(0, 10);
		page = this.repository.findAll(pageable);
		return page;
	}

	// Filtra o nome do fornecedor retira os espacos e deixar maiusculo
	public String filterNameFornecedor(String getname) {
		String filterSpace = null;
		filterSpace = getname.replace(" ", "");
		return filterSpace;
	}

	// Busca pelo nome do fornecedor
	public Fornecedores findByNomeFornecedor(String name) {
		Fornecedores fornecedores = null;
		String nameFilter = null;	
		nameFilter = this.filterNameFornecedor(name);
		fornecedores = this.repository.findByNomeContaining(nameFilter);
		return fornecedores;
	}

	// Se o campo fornecedor_status estiver true ele muda para false e vice-versa
	public void desativarOrAtivarFornecedor(String name) {
		Fornecedores fornecedores = null;
		fornecedores = this.findByNomeFornecedor(name);
		if (fornecedores != null) {

			if (fornecedores.getFornecedoresStatus() == true) {
				this.repository.fornecedorDesative(fornecedores.getId());

			} else {
				this.repository.fornecedorActive(fornecedores.getId());
			}
		}
	}
	public void verifyFornecedores(String name) {
		Fornecedores returnFornecedores =null;
		returnFornecedores = this.repository.findByNomeContaining(name);
		if(returnFornecedores == null) {
			throw new ResourceNotFoundException("Nenhum fornecedor encontrado com o nome: " +name);
		}
	}

}
