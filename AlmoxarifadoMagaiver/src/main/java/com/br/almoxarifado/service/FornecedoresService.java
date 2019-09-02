package com.br.almoxarifado.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.entity.Telefone;
import com.br.almoxarifado.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedoresService {
	@Autowired
	FornecedorRepository repository ;
	@Autowired
	TelefoneService telService;
	
	//Isere somente se nao existir o mesmo nome cadastrado
	public Fornecedores insertFornecedores(Fornecedores fornecedores,Set<Telefone> telefone) {
		Fornecedores findFornecedores = null;
		Fornecedores fornecedoresPersist = null;
		String fornecedorNameFilter = null;
			fornecedorNameFilter = this.filterNameFornecedor(fornecedores.getNome());
			findFornecedores = this.findByNomeFornecedor(fornecedorNameFilter);
			if(findFornecedores == null) {
				fornecedores.setNome(fornecedorNameFilter);
				fornecedoresPersist = this.repository.save(fornecedores);
				this.telService.insertTelefone(telefone);
			}
		
			
	return fornecedoresPersist;
		
	}
	public Fornecedores updateFornecedores(Fornecedores fornecedores) {
	String filterFornecedorName = null;
	Fornecedores fornecedoresPersist = null;
	Fornecedores findFornecedores;
			filterFornecedorName = this.filterNameFornecedor(fornecedores.getNome());
			findFornecedores = this.findByNomeFornecedor(filterFornecedorName);
				if(findFornecedores != null) {
					fornecedoresPersist = this.repository.save(fornecedores);
				}
			
	return fornecedoresPersist;
	}
	//Retorna uma Pagina com 10 fornecedores
	public Page<Fornecedores> findAll() {
		Page<Fornecedores> page;
		PageRequest pageable = PageRequest.of(0, 10);
		page = this.repository.findAll(pageable);
		return page;
	}
	//Filtra o nome do fornecedor retira os espacos e deixar maiusculo
	public String filterNameFornecedor(String getname){
		String filterSpace = null;
		String filterUpperCase = null;
			filterSpace = getname.replace(" ", "");
				filterUpperCase = filterSpace.toUpperCase();
		
		return filterUpperCase;
	}
	//Busca pelo nome do fornecedor
	public Fornecedores findByNomeFornecedor(String name) {
		Fornecedores fornecedores =null;
		String nameFilter = null;
			nameFilter = this.filterNameFornecedor(name);
				fornecedores = this.repository.findByNomeContaining(nameFilter);
				
		return fornecedores;
	}
	

}
