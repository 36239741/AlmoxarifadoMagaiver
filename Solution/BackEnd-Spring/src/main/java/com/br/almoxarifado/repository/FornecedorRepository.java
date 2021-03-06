package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Fornecedor;
@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedor, Long> {
	
	@EntityGraph(attributePaths = {"item"})
	Fornecedor findByNomeIgnoreCase (String name);
	@Modifying
	@Query(value = "UPDATE Fornecedor fornecedor SET fornecedor.fornecedorStatus = 'true' WHERE fornecedor.id = ?1")
	void fornecedorActive(long id);
	@Modifying
	@Query(value = "UPDATE Fornecedor fornecedor SET fornecedor.fornecedorStatus = 'false' WHERE fornecedor.id = ?1")
	void fornecedorDesative(long id);
	
}
