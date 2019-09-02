package com.br.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.almoxarifado.entity.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long	> {
	@Query(value = "SELECT * FROM tbl_telefone WHERE fornecedor_id = ?1 AND tipo_telefone = ?2",nativeQuery = true)
	Telefone  findTelefoneByIdFornecedor (Long idFornecedor,Integer tipoTelefone);
}
