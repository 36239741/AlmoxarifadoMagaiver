package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Fornecedores;
@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedores, Long> {
	@Query(value = "SELECT * FROM tbl_fornecedor WHERE nome ILIKE %?1%",nativeQuery = true)
	Fornecedores findByNomeContaining (String name);
	@Modifying
	@Query(value = "UPDATE tbl_fornecedor SET fornecedores_status = 'true' WHERE fornecedor_id = ?1")
	void fornecedorActive(Long id);
	@Modifying
	@Query(value = "UPDATE tbl_fornecedor SET fornecedores_status = 'false' WHERE fornecedor_id = ?1")
	void fornecedorDesative(Long id);
}
