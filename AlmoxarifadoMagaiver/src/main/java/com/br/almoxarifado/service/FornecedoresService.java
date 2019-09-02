package com.br.almoxarifado.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.almoxarifado.entity.Fornecedores;
import com.br.almoxarifado.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedoresService {
	@Autowired
	FornecedorRepository repository ;

	public Fornecedores insertOrUpdate(Fornecedores fornecedores) {
		
		this.repository.save(fornecedores);
		
		return fornecedores;
		
	}
	

}
