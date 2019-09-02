package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Fornecedores;
@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedores, Long> {
	@Query(value = "SELECT * FROM tbl_fornecedor WHERE nome LIKE ?1",nativeQuery = true)
	Fornecedores findByNomeContaining (String name);
}
