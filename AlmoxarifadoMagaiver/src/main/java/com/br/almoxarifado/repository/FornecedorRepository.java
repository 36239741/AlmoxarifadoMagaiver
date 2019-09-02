package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Fornecedores;
@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedores, Long> {
	
}
