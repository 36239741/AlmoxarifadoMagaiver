package com.br.almoxarifado.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.br.almoxarifado.dto.DtoFornecedor;
import com.br.almoxarifado.dto.DtoFornecedorItem;
import com.br.almoxarifado.entity.Fornecedor;
import com.br.almoxarifado.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedorService {
	@Autowired
	FornecedorRepository repository;

	/*
	 * @param Fornecedores
	 * a funcao ira inserir um fornecedor 
	 * @return return the save object
	 */	
	public DtoFornecedor insertFornecedores(DtoFornecedor dtoFornecedor) {
		Fornecedor returnFornecedores = null;
		
		returnFornecedores = this.repository.save(this.convertDtoFornecedor(dtoFornecedor));
		
		return this.convertFornecedor(returnFornecedores);
				
	}
	
	/*
	 * @param integer page, integer pageSize
	 * ira fazer um find all e retornara em pageable
	 * @return page fornecedores
	 */		
	@Transactional(readOnly = true)
	public Page<Fornecedor> findAll(Integer page, Integer pageSize) {
		Page<Fornecedor> pageFornecedores;
		PageRequest pageable = PageRequest.of(page, pageSize);
		pageFornecedores = this.repository.findAll(pageable);
		return pageFornecedores;
	}


	/*
	 * @param String nome do fornecedor
	 * buscara o fornedor pelo nome
	 * @return Fornecedores
	 */
	@Transactional(readOnly = true)
	public Fornecedor findByNomeFornecedor(String name) {
		Fornecedor returnFornecedores = this.repository.findByNomeIgnoreCase(name);
		Assert.notNull(returnFornecedores, "Fornecedor "+name+" nao encontrado");
		return returnFornecedores;
	}

	/*
	 * @param String nome do fornecedor
	 * ira desativar e ativar o fornecedor
	 * @return void
	 */
	
	public Boolean desativarOrAtivarFornecedor(String name) {
		Fornecedor fornecedores = null;
		Boolean returnStatus = null;
		fornecedores = this.findByNomeFornecedor(name);
		if (fornecedores != null) {

			if (fornecedores.getFornecedorStatus()) {
				 this.repository.fornecedorDesative(fornecedores.getFornecedorId());
				 returnStatus = false;
			} 
			else {
				 this.repository.fornecedorActive(fornecedores.getFornecedorId());
				 returnStatus = true;
			}
			
		}
		return returnStatus;
	}

							/* METODOS DE CONVERSAO DTO */
	
		public Fornecedor convertDtoFornecedor(DtoFornecedor dtoFornecedor) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dtoFornecedor, Fornecedor.class);
		
	}
		public DtoFornecedor convertFornecedor(Fornecedor fornecedor) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(fornecedor, DtoFornecedor.class);
		
	}
		public DtoFornecedorItem convertFornecedorItem(Fornecedor fornecedor) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(fornecedor, DtoFornecedorItem.class);
		
	}
	



}
